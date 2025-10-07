package com.weilai.minio.configuration;
import com.weilai.minio.constant.MinioConstant;
import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
// 确保配置类被spring管理，注册为Bean，或者在MinioProperties类中添加@Component注解
//@EnableConfigurationProperties(MinioProperties.class)
// 控制当前配置类是否启用
@ConditionalOnProperty(prefix = MinioConstant.PREFIX, value = "enabled", matchIfMissing = true)
public class MinioConfiguration {

    private final MinioProperties properties;

    /**
     * 构造函数注入MinioProperties配置属性
     *
     * @param properties MinIO配置属性
     */
    public MinioConfiguration(MinioProperties properties) {
        this.properties = properties;
    }

    /**
     * 创建MinioClient同步客户端实例
     * 当容器中不存在MinioClient Bean时创建
     *
     * @return MinioClient同步客户端实例
     */
    @Bean
    public MinioClient minioClient() {
        // 使用 MinioClient.builder() 创建客户端实例
        return MinioClient.builder()
                .endpoint(properties.getEndpoint())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();
    }

}
