package com.weilai.minio.service;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.HashMultimap;
import com.weilai.minio.config.CustomMinioClient;
import com.weilai.minio.config.MinioProperties;
import com.weilai.minio.constant.MinioConstant;
import com.weilai.minio.entity.File;
import com.weilai.minio.exceptions.MinioServiceException;
import com.weilai.minio.enums.PolicyType;
import com.weilai.minio.mapper.FileMapper;
import com.weilai.minio.utils.CustomUtil;
import com.wisark.api.feign.user.UserClient;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.weilai.common.constants.CacheConstant.FILE_INFO_EXPIRE;

/**
 * 类 MinioService 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2021/11/28 00:15
 */
@Service
@Slf4j
public class MinioService {

    private final MinioProperties properties;


    private final MinioClient minioClient;


    @Resource
    private CustomMinioClient customMinioClient;


    public MinioService(MinioProperties properties, MinioClient minioClient) {
        this.properties = properties;
        this.minioClient = minioClient;
    }

    /**
     * 查看指定bucket是否存在
     *
     * @param bucketName bucket名称
     * @return
     */
    public boolean bucketExists(String bucketName) throws MinioServiceException {
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (ErrorResponseException | IOException | InsufficientDataException
                 | InternalException | InvalidKeyException | InvalidResponseException
                 | NoSuchAlgorithmException | XmlParserException | ServerException e) {
            throw new MinioServiceException("Error checking if bucket exists: " + bucketName, e);
        }
    }

    // =========================================桶操作=====================================

    /**
     * 创建一个bucket
     *
     * @param bucketName bucket名称
     * @return
     */
    public boolean createBucket(String bucketName) throws MinioServiceException {
        try {
            if (!bucketExists(bucketName)) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            return true;
        } catch (ErrorResponseException | IOException | InsufficientDataException
                 | InternalException | InvalidKeyException | InvalidResponseException
                 | NoSuchAlgorithmException | XmlParserException | ServerException e) {
            throw new MinioServiceException("Failed to create bucket: " + bucketName, e);
        }
    }

    /**
     * 创建一个bucket，并指定访问策略
     *
     * @param bucketName bucket名称
     * @param policyType 访问策略
     * @return
     */
    public boolean createBucket(String bucketName, PolicyType policyType) throws MinioServiceException {
        try {
            if (this.createBucket(bucketName)) {
                minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(PolicyType.getPolicy(policyType, bucketName)).build());
            }
            return true;
        } catch (ErrorResponseException | IOException | InsufficientDataException
                 | InternalException | InvalidKeyException | InvalidResponseException
                 | NoSuchAlgorithmException | XmlParserException | ServerException e) {
            throw new MinioServiceException("Failed to create bucket: " + bucketName, e);
        }
    }

    /**
     * 指定访问策略
     *
     * @param bucketName bucket名称
     * @param policyType 访问策略
     * @return
     */
    public boolean setBucketPolicy(String bucketName, PolicyType policyType) throws MinioServiceException {
        try {
            if (!bucketExists(bucketName)) {
                throw new MinioServiceException(bucketName + " bucket does not exist.");
            }
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(PolicyType.getPolicy(policyType, bucketName)).build());
            return true;
        } catch (ErrorResponseException | IOException | InsufficientDataException
                 | InternalException | InvalidKeyException | InvalidResponseException
                 | NoSuchAlgorithmException | XmlParserException | ServerException e) {
            throw new MinioServiceException("Failed to set bucket policy for bucket: " + bucketName, e);
        }
    }


    /**
     * 删除一个bucket
     *
     * @param bucketName bucket名称
     * @return
     */
    public boolean removeBucket(String bucketName) throws MinioServiceException {
        try {
            if (bucketExists(bucketName)) {
                minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
            }
            return true;
        } catch (ErrorResponseException | IOException | InsufficientDataException
                 | InternalException | InvalidKeyException | InvalidResponseException
                 | NoSuchAlgorithmException | XmlParserException | ServerException e) {
            throw new MinioServiceException("Failed to remove bucket: " + bucketName, e);
        }
    }

    // =================================文件操作==================================

    /**
     * 获得外链，永久有效
     *
     * @param objectName 文件
     * @param isForever  是不是永久有效
     * @return
     */
    public String getObjectUrl(String objectName, boolean isForever) throws MinioServiceException {
        if (isForever) {
            return getObjectUrl(getBucketName(), objectName, false, 30000);
        }
        return getObjectUrl(getBucketName(), objectName);
    }


    /**
     * 获得外链，过期时间默认7天
     *
     * @param objectName 文件
     * @return
     */
    public String getObjectUrl(String objectName) throws MinioServiceException {
        return getObjectUrl(objectName, false);
    }

    /**
     * 获得外链，过期时间默认7天
     *
     * @param bucketName bucket 名称
     * @param objectName 文件
     * @return
     */
    public String getObjectUrl(String bucketName, String objectName) throws MinioServiceException {
        return getObjectUrl(bucketName, objectName, false);
    }

    /**
     * 获得外链，过期时间默认7天
     *
     * @param bucketName     bucket 名称
     * @param objectName     文件
     * @param replaceAddress 是否替换访问域名
     * @return
     */
    public String getObjectUrl(String bucketName, String objectName, boolean replaceAddress) throws MinioServiceException {
        return getObjectUrl(bucketName, objectName, replaceAddress, GetPresignedObjectUrlArgs.DEFAULT_EXPIRY_TIME);
    }

    /**
     * 获得外链
     *
     * @param bucketName     bucket 名称
     * @param objectName     文件
     * @param replaceAddress 是否替换访问域名
     * @param expires        过期时间，单位秒
     * @return
     */
    public String getObjectUrl(String bucketName, String objectName, boolean replaceAddress, Integer expires) throws MinioServiceException {
        try {
            String objectUrl = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucketName).object(CustomUtil.getObjectName(objectName)).expiry(expires).method(Method.GET).build());
            return replaceAddress && properties.getAddress() != null ? objectUrl.replace(properties.getEndpoint(), properties.getAddress()) : objectUrl;
        } catch (ErrorResponseException | IOException | InsufficientDataException
                 | InternalException | InvalidKeyException | InvalidResponseException
                 | NoSuchAlgorithmException | XmlParserException | ServerException e) {
            throw new MinioServiceException("Failed to get presigned URL for object: " + objectName + " in bucket: " + bucketName, e);
        }
    }

    /**
     * 上传文件
     *
     * @param objectName 文件名称，如：2021/11/28/test.zip
     * @param stream     文件流
     * @return
     */
    public ObjectWriteResponse putObject(String objectName, InputStream stream) throws MinioServiceException {
        return putObject(getBucketName(), objectName, stream);
    }

    /**
     * 上传文件
     *
     * @param contentType 文件mime类型
     * @param objectName  文件名称，如：2021/11/28/test.zip
     * @param stream      文件流
     * @return
     */
    public ObjectWriteResponse putObject(String objectName, String contentType, InputStream stream) throws MinioServiceException {
        return putObject(properties.getBucketName(), objectName, contentType, stream);
    }

    /**
     * 上传文件
     *
     * @param bucketName  bucket名称
     * @param objectName  文件名称，如：2021/11/28/test.zip
     * @param contentType 文件类型
     * @param stream      文件流
     * @return
     */
    public ObjectWriteResponse putObject(String bucketName, String objectName, String contentType,
                                         InputStream stream) throws MinioServiceException {
        try {
            return putObject(bucketName, objectName, contentType, stream, stream.available(), ObjectWriteArgs.MIN_MULTIPART_SIZE);
        } catch (IOException e) {
            throw new MinioServiceException("Failed to upload object: " + objectName + " to bucket: " + bucketName, e);
        }
    }

    /**
     * 上传文件
     *
     * @param bucketName  bucket名称
     * @param objectName  文件名称，如：2021/11/28/test.zip
     * @param contentType 文件类型
     * @param stream      文件流
     * @param objectSize  文件大小
     * @return
     */
    public ObjectWriteResponse putObject(String bucketName, String objectName, String contentType,
                                         InputStream stream, long objectSize) throws MinioServiceException {
        return putObject(bucketName, objectName, contentType, stream, objectSize, -1);
    }

    /**
     * 上传文件
     *
     * @param bucketName  bucket名称
     * @param objectName  文件名称，如：2021/11/28/test.zip
     * @param contentType 文件类型
     * @param stream      文件流
     * @param objectSize  文件大小
     * @param partSize    分片大小
     * @return
     */
    public ObjectWriteResponse putObject(String bucketName, String objectName, String contentType,
                                         InputStream stream, long objectSize, long partSize) throws MinioServiceException {
        try {
            return minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(CustomUtil.getObjectName(objectName))
                    .stream(stream, objectSize, partSize)
                    .contentType(CustomUtil.getContentType(contentType))
                    .build());
        } catch (ErrorResponseException | IOException | InsufficientDataException
                 | InternalException | InvalidKeyException | InvalidResponseException
                 | NoSuchAlgorithmException | XmlParserException | ServerException e) {
            throw new MinioServiceException("Failed to upload object: " + objectName + " to bucket: " + bucketName, e);
        }
    }


    /**
     * 单个删除
     *
     * @param objectName
     * @return
     */
    public boolean removeObject(String objectName) throws MinioServiceException {
        return removeObject(getBucketName(), objectName);
    }

    /**
     * 单个删除
     *
     * @param bucketName
     * @param objectName
     * @return
     */
    public boolean removeObject(String bucketName, String objectName) throws MinioServiceException {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(CustomUtil.getObjectName(objectName)).build());
            return true;
        } catch (ErrorResponseException | IOException | InsufficientDataException
                 | InternalException | InvalidKeyException | InvalidResponseException
                 | NoSuchAlgorithmException | XmlParserException | ServerException e) {
            throw new MinioServiceException("Failed to remove object: " + objectName + " from bucket: " + bucketName, e);
        }
    }

    /**
     * 批量删除
     *
     * @param bucketName
     * @param objectNames
     * @return
     */
    public List<String> removeObjects(String bucketName, Collection<String> objectNames) throws MinioServiceException {
        List<DeleteObject> objects = objectNames.stream().map(CustomUtil::getObjectName).map(DeleteObject::new).collect(Collectors.toList());
        Iterable<io.minio.Result<DeleteError>> results = minioClient.removeObjects(RemoveObjectsArgs.builder().bucket(bucketName).objects(objects).build());
        List<String> errorDeleteObjects = new ArrayList<>();
        try {
            for (io.minio.Result<DeleteError> result : results) {
                errorDeleteObjects.add(result.get().objectName());
                log.error(String.format("Error in deleting object %s:%s, code=%s, message=%s",
                        bucketName, result.get().objectName(), result.get().code(), result.get().message()));
            }
        } catch (ErrorResponseException | IOException | InsufficientDataException
                 | InternalException | InvalidKeyException | InvalidResponseException
                 | NoSuchAlgorithmException | XmlParserException | ServerException e) {
            throw new MinioServiceException("Failed to remove objects from bucket: " + bucketName, e);
        }
        return errorDeleteObjects;
    }


    /**
     * 获取上传文件的url
     *
     * @param objectName
     * @return
     * @throws MinioException
     */
    public String getPresignedObjectPutUrl(String objectName) throws MinioServiceException {
        return getPresignedObjectPutUrl(getBucketName(), objectName);
    }

    /**
     * 获取上传文件的url
     *
     * @param bucketName
     * @param objectName
     * @return
     * @throws MinioException
     */
    public String getPresignedObjectPutUrl(String bucketName, String objectName) throws MinioServiceException {
        return getPresignedObjectPutUrl(bucketName, null, objectName);
    }

    /**
     * 获取上传文件的url
     *
     * @param bucketName
     * @param path
     * @param objectName
     * @return
     * @throws MinioException
     */
    public String getPresignedObjectPutUrl(String bucketName, String path, String objectName) throws MinioServiceException {
        return getPresignedObjectPutUrl(bucketName, path, objectName, 5);
    }

    /**
     * 获取上传文件的url
     *
     * @param bucketName
     * @param path
     * @param objectName
     * @return
     * @throws MinioException
     */
    public String getPresignedObjectPutUrl(String bucketName, String path, String objectName, Integer time) throws MinioServiceException {
        return getPresignedObjectPutUrl(bucketName, path, objectName, time, TimeUnit.MINUTES);
    }

    /**
     * 获取上传文件的url
     *
     * @param bucketName
     * @param path
     * @param objectName
     * @param time
     * @param timeUnit
     * @return
     * @throws MinioException
     */
    public String getPresignedObjectPutUrl(String bucketName, String path, String objectName, Integer time, TimeUnit timeUnit) throws MinioServiceException {
        try {
            objectName = (StringUtils.hasText(path) ? CustomUtil.getPath(path) : "") + CustomUtil.getObjectName(objectName);
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.PUT)
                    .bucket(bucketName)
                    .object(objectName)
                    .expiry(time, timeUnit).build());
        } catch (ErrorResponseException | IOException | InsufficientDataException
                 | InternalException | InvalidKeyException | InvalidResponseException
                 | NoSuchAlgorithmException | XmlParserException | ServerException e) {
            throw new MinioServiceException("Failed to get presigned PUT URL for object: " + objectName + " in bucket: " + bucketName, e);
        }
    }


    /**
     * 获得 当前日期分割目录，如：2021/11/28
     *
     * @return
     */
    public String getDatePath() {
        return String.join(MinioConstant.URI_DELIMITER, CustomUtil.getDateFolder());
    }


//    /**
//     * 获得文件外链，永久外链，需要服务端设置指定bucket为全部可访问
//     *
//     * @param bucket    bucket名称
//     * @param finalPath 路径及文件名，如 2021/11/20/test.zip
//     * @return
//     */
//    public String gatewayUrl(String bucket, String finalPath) {
//        finalPath = finalPath.startsWith(MinioConstant.URI_DELIMITER) ? finalPath.substring(finalPath.indexOf(MinioConstant.URI_DELIMITER) + 1) : finalPath;
//        return getAddress(properties.getEndpoint() + MinioConstant.URI_DELIMITER + bucket + MinioConstant.URI_DELIMITER + finalPath);
//    }


//
//    /**
//     * 获得文件访问地址
//     *
//     * @return
//     */
//    public String getAddress(String url) {
//        String address = "".equals(properties.getAddress().trim()) ? properties.getEndpoint() : properties.getAddress();
//        return url.replace(properties.getEndpoint(), address);
//    }

    /**
     * 默认BucketName
     *
     * @return
     */
    public String getBucketName() {
        if (!StringUtils.hasText(properties.getBucketName())) {
            throw new RuntimeException("未配置默认 BucketName");
        }
        return properties.getBucketName();
    }


    /**
     * 获取已经上传的分片索引
     *
     * @param object
     * @param uploadId
     * @param bucket
     * @return
     */
    public List<Integer> getChunkByMD5(String object, String uploadId, String bucket) {
        try {
            // 查询上传后的分片数据
            ListPartsResponse partResult = customMinioClient.listMultipart(bucket, null, object, 1000, 0, uploadId, null, null);
            return partResult.result().partList().stream().map(Part::partNumber).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("error message: 查询上传后的分片信息失败、原因:", e);
            return null;
        }
    }


    /**
     * 初始化分片上传
     *
     * @param file
     * @return Mono<Map < String, Object>>
     */
    public Map<String, Object> initMultiPartUpload(File file) {
        // 返回数据
        Map<String, Object> resMap = new HashMap<>();
        try {
            if (StrUtil.isBlank(file.getContentType())) {
                // 没有文件类型，默认是application/octet-stream,默认是下载
                file.setContentType("application/octet-stream");
            }
            HashMultimap<String, String> headers = HashMultimap.create();
            headers.put("Content-Type", file.getContentType());

            //获取uploadId
            String uploadId = null;
            if (StrUtil.isBlank(file.getUploadId())) {
                uploadId = customMinioClient.initMultiPartUpload(file.getBucket(), null, file.getFileName(), headers, null);
            } else {
                uploadId = file.getUploadId();
            }

            resMap.put("uploadId", uploadId);
            file.setUploadId(uploadId);

            // 获取分片上传地址
            List<String> partList = new ArrayList<>();
            Map<String, String> reqParams = new HashMap<>();
            for (int i = 1; i <= file.getChunkNum(); i++) {
                reqParams.put("partNumber", String.valueOf(i));
                // 加入uploadId
                reqParams.put("uploadId", uploadId);
                String uploadUrl = customMinioClient.getPresignedObjectUrl(
                        GetPresignedObjectUrlArgs.builder()
                                .method(Method.PUT)
                                .bucket(file.getBucket())
                                .object(file.getFileName())
                                .expiry(FILE_INFO_EXPIRE, TimeUnit.SECONDS)
                                .extraQueryParams(reqParams)
                                .build());
                partList.add(uploadUrl);
            }
            resMap.put("urlList", partList);
            resMap.put("code", 200);
            return resMap;
        } catch (Exception e) {
            log.error("error message: 初始化分片上传失败、原因:", e);
            // 返回 文件上传失败
            return null;
        }
    }


    /**
     * 单文件签名上传
     *
     * @param objectName 文件全路径名称
     * @param bucketName 桶名称
     * @return /
     */
    public Map<String, Object> getUploadObjectUrl(String objectName, String bucketName) {
        try {
            Map<String, Object> resMap = new HashMap();
            List<String> partList = new ArrayList<>();
            String url = customMinioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.PUT)
                            .bucket(bucketName)
                            .object(objectName)
                            .expiry(FILE_INFO_EXPIRE, TimeUnit.SECONDS)
                            .build());
            partList.add(url);
            resMap.put("uploadId", "SingleFileUpload");
            resMap.put("urlList", partList);
            return resMap;
        } catch (Exception e) {
            log.error("error message: 单个文件上传失败、原因:", e);
            // 返回 文件上传失败
            return null;
        }
    }


    /**
     * 分片上传完后合并
     *
     * @param objectName 文件全路径名称
     * @param uploadId   返回的uploadId
     * @param bucketName 桶名称
     * @return boolean
     */
    public boolean mergeMultipartUpload(String objectName, String uploadId, String bucketName) {
        try {

            // 查询上传后的分片数据
            ListPartsResponse partResult = customMinioClient.listMultipart(bucketName, null, objectName, 1000, 0, uploadId, null, null);

            if (partResult.result().partList() == null || partResult.result().partList().isEmpty()) {
                log.error("未找到任何分片数据，uploadId: {}", uploadId);
                return false;
            }

            // 根据实际分片数量创建Part数组
            List<Part> partList = partResult.result().partList();
            Part[] parts = new Part[partList.size()];

            // 使用原始分片编号
            for (int i = 0; i < partList.size(); i++) {
                Part part = partList.get(i);
                parts[i] = new Part(part.partNumber(), part.etag());
            }

            // 合并分片
            customMinioClient.mergeMultipartUpload(bucketName, null, objectName, uploadId, parts, null, null);
            return true;

        } catch (Exception e) {
            log.error("error message: 合并失败、原因:", e);
            return false;
        }
    }


}
