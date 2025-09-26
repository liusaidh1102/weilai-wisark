package com.weilai.minio.service;
import com.weilai.minio.configuration.MinioProperties;
import com.weilai.minio.constant.MinioConstant;
import com.weilai.minio.entity.FileInfo;
import com.weilai.minio.exceptions.MinioServiceException;
import com.weilai.minio.enums.PolicyType;
import com.weilai.minio.mapper.FileMapper;
import com.weilai.minio.utils.CustomUtil;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
/**
 * 类 MinioService 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2021/11/28 00:15
 */
@Service
@Slf4j
public class FileService {

    private final MinioProperties properties;


    private final MinioClient minioClient;

    @Autowired
    private FileMapper fileMapper;

    public FileService(MinioProperties properties, MinioClient minioClient) {
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
     * 上传文件 返回FileInfo作为文件信息
     * @param file
     * @return
     */
    public FileInfo uploadFile(MultipartFile file){
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
        if (fileExtension == null){
            log.error("无法获取文件扩展名: {}", originalFilename);
            return null;
        }
        
        String fileUploadName = CustomUtil.getObjectUploadName(originalFilename);
        try {
            putObject(fileUploadName, file.getContentType(), file.getInputStream());
            // 获取文件签名url
            String url = getObjectUrl(fileUploadName);
            FileInfo fileInfo = new FileInfo(
                    fileUploadName,
                    originalFilename,
                    CustomUtil.getFileExtension(originalFilename),
                    url,
                    // 获取当前用户的id
                    1L,
                    1L);
            // 数据库保存对应的信息
            fileMapper.insert(fileInfo);
            return fileInfo;
        }catch (MinioServiceException | IOException e){
            log.error("上传文件失败：{}", e.getMessage(), e);
            return null;
        }
    }


    /**
     * 获得外链，过期时间默认7天
     *
     * @param objectName 文件
     * @return
     */
    public String getObjectUrl(String objectName) throws MinioServiceException {
        return getObjectUrl(getBucketName(), objectName);
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
     * @param objectName 文件名称，如：2021/11/28/test.zip
     * @param stream     文件流
     * @return
     */
    public ObjectWriteResponse putObject( String objectName, String contentType, InputStream stream) throws MinioServiceException {
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
        Iterable<Result<DeleteError>> results = minioClient.removeObjects(RemoveObjectsArgs.builder().bucket(bucketName).objects(objects).build());
        List<String> errorDeleteObjects = new ArrayList<>();
        try {
            for (Result<DeleteError> result : results) {
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

    /**
     * 获得文件外链，永久外链，需要服务端设置指定bucket为全部可访问
     *
     * @param bucket    bucket名称
     * @param finalPath 路径及文件名，如 2021/11/20/test.zip
     * @return
     */
    public String gatewayUrl(String bucket, String finalPath) {
        finalPath = finalPath.startsWith(MinioConstant.URI_DELIMITER) ? finalPath.substring(finalPath.indexOf(MinioConstant.URI_DELIMITER) + 1) : finalPath;
        return getAddress(properties.getEndpoint() + MinioConstant.URI_DELIMITER + bucket + MinioConstant.URI_DELIMITER + finalPath);
    }

    /**
     * 获得文件访问地址
     *
     * @return
     */
    public String getAddress(String url) {
        String address = "".equals(properties.getAddress().trim()) ? properties.getEndpoint() : properties.getAddress();
        return url.replace(properties.getEndpoint(), address);
    }

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

}
