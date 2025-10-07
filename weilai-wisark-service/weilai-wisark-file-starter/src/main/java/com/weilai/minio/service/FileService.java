package com.weilai.minio.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.weilai.common.response.Result;
import com.weilai.minio.entity.File;
import com.weilai.minio.entity.FileDTO;
import com.weilai.minio.exceptions.MinioServiceException;
import com.weilai.minio.mapper.FileMapper;
import com.weilai.minio.utils.CustomUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import static com.weilai.common.constants.CacheConstant.FILE_INFO_EXPIRE;
import static com.weilai.common.constants.CacheConstant.FILE_INFO_PREFIX;
import static com.weilai.common.response.CodeEnum.*;
import static com.weilai.common.response.CodeEnum.FILE_ERROR;
@Service
@Slf4j
public class FileService {

    @Resource
    private FileMapper fileMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private MinioService minioService;


    /**
     * 上传文件 返回FileInfo作为文件信息
     *
     * @param file
     * @return
     */
    public File uploadFile(MultipartFile file) {
        // 参数校验
        if (file == null || file.isEmpty()) {
            log.error("上传文件为空");
            return null;
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            log.error("原始文件名为空");
            return null;
        }

        String fileExtension = CustomUtil.getFileExtension(originalFilename);
        if (fileExtension == null) {
            log.error("无法获取文件扩展名: {}", originalFilename);
            return null;
        }

        String fileUploadName = CustomUtil.getObjectUploadName(originalFilename);
        try {
            minioService.putObject(fileUploadName, file.getContentType(), file.getInputStream());
            // 获取文件签名url
            String url = minioService.getObjectUrl(fileUploadName, true);
            File fileInfo = new File(
                    fileUploadName,
                    originalFilename,
                    CustomUtil.getFileExtension(originalFilename),
                    url);
            // 数据库保存对应的信息
            fileMapper.insert(fileInfo);
            return fileInfo;
        } catch (MinioServiceException | IOException e) {
            log.error("上传文件失败：{}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 校验文件是否存在
     *
     * @param md5
     * @return
     */
    public Result<File> checkFile(String md5) {
        LambdaQueryWrapper<File> queryWrapper = new LambdaQueryWrapper<File>()
                .eq(File::getMd5, md5);
        File file = fileMapper.selectOne(queryWrapper);
        // 秒传
        if (file != null) {
            return Result.ok(FILE_OK, file);
        }
        String key = FILE_INFO_PREFIX + md5;
        String fileInfoStr = stringRedisTemplate.opsForValue().get(key);
        if (fileInfoStr != null) {
            file = JSONUtil.toBean(fileInfoStr, File.class);
            // 断点续传
            List<Integer> uploadIds = minioService.getChunkByMD5(file.getFileName(), file.getUploadId(), file.getBucket());
            file.setUploadedChunks(uploadIds);
            // 返回已经上传的分片索引
            return Result.ok(FILE_UPLOAD_ING, file);
        }
        return Result.fail(FILE_NOT_EXISTS);
    }


    public Result<?> initMultiPartUpload(FileDTO fileDTO) {
        File file = BeanUtil.copyProperties(fileDTO, File.class);
        String key = FILE_INFO_PREFIX + fileDTO.getMd5();
        String fileStr = stringRedisTemplate.opsForValue().get(key);
        // 缓存中有文件信息
        if (fileStr != null) {
            file = JSONUtil.toBean(fileStr, File.class);
        }

        String originFileName = fileDTO.getOriginalName();
        // 文件上传的object路径
        String fileName = CustomUtil.getObjectUploadName(originFileName);
        file.setFileName(fileName);
        file.setBucket(minioService.getBucketName());

        // 获取文件访问路径
        String url = null;
        try {
            url = minioService.getObjectUrl(fileName, true);
        } catch (MinioServiceException e) {
            log.error("error message: 获取文件访问路径失败、原因:", e);
            return Result.fail("获取文件访问路径失败，请重试！");
        }
        file.setUrl(url);

//        // 分片上传
//        Integer chunkNum = fileDTO.getChunkNum();
//        //就一个分片，直接上传整个文件
//        if (chunkNum.equals(1)) {
//            Map<String, Object> uploadObjectUrl = minioService.getUploadObjectUrl(fileName, "");
//            return Result.ok(uploadObjectUrl);
//        }
        // 设置文件的content-type
        Map<String, Object> map = minioService.initMultiPartUpload(file);
        if (map == null) {
            return Result.fail(FILE_ERROR, "初始化上传失败，请稍后再试！");
        }
        String uploadId = (String) map.get("uploadId");
        file.setUploadId(uploadId);
        // 将文件信息存入redis中, 设置一天过期
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(file), FILE_INFO_EXPIRE);
        return Result.ok(FILE_INIT_OK,map);
    }


    /**
     * 合并分片信息
     *
     * @param md5
     * @return
     */
    public Result<File> mergeMultipartUpload(String md5) {
        String key = FILE_INFO_PREFIX + md5;
        String fileStr = stringRedisTemplate.opsForValue().get(key);
        if (fileStr == null) {
            return Result.fail(FILE_ERROR, "文件合并失败，请先初始化");
        }
        File file = JSONUtil.toBean(fileStr, File.class);
        // 添加分片验证逻辑
        String fileName = file.getFileName();
        String uploadId = file.getUploadId();
        String bucket = file.getBucket();
        List<Integer> chunkList = minioService.getChunkByMD5(fileName, uploadId, bucket);
        // 检查分片是不是缺少
        if (CollectionUtil.isEmpty(chunkList) || chunkList.size() != file.getChunkNum()) {
            stringRedisTemplate.delete(key);
            return Result.fail(FILE_ERROR, "文件合并失败，分片信息不完整");
        }

        //根据文件的fileName和uploadId和bucketName上传
        boolean result = minioService.mergeMultipartUpload(fileName, file.getUploadId(), bucket);
        //合并成功
        if (result) {
            //存入数据库
            fileMapper.insert(file);
            stringRedisTemplate.delete(key);
            return Result.ok(FILE_OK, "文件上传成功！", file);
        }
        return Result.fail(FILE_ERROR, "文件上传失败，请重试！");
    }
}