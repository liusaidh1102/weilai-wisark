package com.kangaroohy.minio.configuration;
import com.kangaroohy.minio.constant.MinioConstant;
import com.kangaroohy.minio.service.MinioService;
import com.kangaroohy.minio.service.client.ExtendMinioAsyncClient;
import com.kangaroohy.minio.service.client.MinioClientProvider;
import com.kangaroohy.minio.service.client.MinioClientProviderImpl;
import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 类 MinioAutoConfiguration 功能描述：
 * MinIO自动配置类，用于在Spring Boot应用中自动配置MinIO相关组件
 * 当满足特定条件时，会自动创建MinIO客户端和服务实例
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2021/11/28 00:15
 */
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
@ConditionalOnClass(MinioService.class)
@ConditionalOnProperty(prefix = MinioConstant.PREFIX, value = "enabled", matchIfMissing = true)
public class MinioAutoConfiguration {

    private final MinioProperties properties;

    /**
     * 构造函数注入MinioProperties配置属性
     *
     * @param properties MinIO配置属性
     */
    public MinioAutoConfiguration(MinioProperties properties) {
        this.properties = properties;
    }

    /**
     * 创建MinioClientProvider实例
     * 当容器中不存在MinioClientProvider Bean时创建
     *
     * @return MinioClientProvider实现类实例
     */
    @Bean
    @ConditionalOnMissingBean(MinioClientProvider.class)
    public MinioClientProvider minioClientProvider() {
        return new MinioClientProviderImpl();
    }

    /**
     * 创建ExtendMinioAsyncClient异步客户端实例
     * 当容器中不存在ExtendMinioAsyncClient Bean时创建
     *
     * @param minioClientProvider MinIO客户端提供者
     * @return ExtendMinioAsyncClient异步客户端实例
     */
    @Bean
    @ConditionalOnMissingBean(ExtendMinioAsyncClient.class)
    public ExtendMinioAsyncClient extendMinioAsyncClient(MinioClientProvider minioClientProvider) {
        return minioClientProvider.getAsyncClient(properties.getEndpoint(), properties.getAccessKey(), properties.getSecretKey());
    }

    /**
     * 创建MinioClient同步客户端实例
     * 当容器中不存在MinioClient Bean时创建
     *
     * @param minioClientProvider MinIO客户端提供者
     * @return MinioClient同步客户端实例
     */
    @Bean
    @ConditionalOnMissingBean(MinioClient.class)
    public MinioClient minioClient(MinioClientProvider minioClientProvider) {
        return minioClientProvider.getClient(properties.getEndpoint(), properties.getAccessKey(), properties.getSecretKey());
    }

    /**
     * 创建自定义MinioService服务实例
     * 当容器中不存在MinioService Bean时创建
     *
     * @param extendMinioAsyncClient 扩展的异步客户端
     * @param minioClient            同步客户端
     * @return MinioService服务实例
     */
    @Bean
    @ConditionalOnMissingBean(MinioService.class)
    public MinioService minioService(ExtendMinioAsyncClient extendMinioAsyncClient, MinioClient minioClient) {
        return new MinioService(properties, extendMinioAsyncClient, minioClient);
    }
}
