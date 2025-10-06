package com.weilai.common.config;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Collections;
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
    public OpenAPI customOpenAPI() {
        Server server = new Server()
                .url("http://82.156.115.174:8080");

        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .version(version)
                        .description(desc))
                .components(new Components()
                        .addSecuritySchemes("Authorization",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization")))
                .addSecurityItem(new SecurityRequirement().addList("Authorization"))
                .servers(Collections.singletonList(server));
    }

}