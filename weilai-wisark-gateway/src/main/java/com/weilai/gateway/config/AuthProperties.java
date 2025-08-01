package com.weilai.gateway.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 认证配置属性
 */
@Component
@ConfigurationProperties(prefix = "wisark.auth")
@Data
public class AuthProperties {

    /*
     * 排除路径
     */
    private List<String> excludePaths;

    /*
     * 包含路径
     */
    private List<String> includePaths;

}
