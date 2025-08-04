package com.weilai.gateway.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

/**
 * 网关聚合swagger
 */
@Configuration
public class SwaggerProvider {

    private final RouteDefinitionLocator routeDefinitionLocator;


    public SwaggerProvider(RouteDefinitionLocator routeDefinitionLocator) {
        this.routeDefinitionLocator = routeDefinitionLocator;
    }

//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title(" API Gateway")
//                        .version("1.0")
//                        .description("API Gateway for microservices")
//                        .contact(new Contact()
//                                .name("Admin")
//                                .email("admin@example.com")));
//    }

    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters
    ) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = routeDefinitionLocator.getRouteDefinitions().collectList().block();

        if (definitions != null) {
            definitions.forEach(routeDefinition -> {
                String group = routeDefinition.getId();
                swaggerUiConfigParameters.addGroup(group);
                groups.add(GroupedOpenApi.builder()
                        .group(group)
                        // 应该是默认去对应的服务扫描
//                                        .packagesToScan("com.weilai.user.controller")
                        // 匹配所有接口
                        .pathsToMatch("/**")
                        .build());
            });
        }
        return groups;
    }
}