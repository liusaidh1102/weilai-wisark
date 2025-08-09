package com.weilai.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "minio")
public class MinioConfigProperties {

    private String accessKey;

    private String secretKey;

    private String endpoint;

    private String bucketName;

}
