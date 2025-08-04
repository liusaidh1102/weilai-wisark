package com.weilai.common.config;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties(prefix = "swagger")
@Data
public class SwaggerConfig {

    /*
    标题
     */
    private String title;

    /*
    描述
     */
    private String desc;

    /*
    版本
     */
    private String version;

    /*
    外部文档地址
     */
    private String externalUrl;


    /*
    外部文档 描述
     */
    private String externalDesc;



    @Bean
    public OpenAPI swaggerOpenApi() {
        return new OpenAPI()
                .info(new Info().title(title)
                        .description(desc)
                        .version(version))
                .externalDocs(new ExternalDocumentation()
                        .description(externalDesc)
                        .url(externalUrl));
    }
}