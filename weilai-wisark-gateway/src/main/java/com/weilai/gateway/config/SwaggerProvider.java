package com.weilai.gateway.config;

import com.weilai.gateway.enums.SwaggerEnum;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
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

    private static final Logger log = LoggerFactory.getLogger(SwaggerProvider.class);
    private final RouteDefinitionLocator routeDefinitionLocator;


    public SwaggerProvider(RouteDefinitionLocator routeDefinitionLocator) {
        this.routeDefinitionLocator = routeDefinitionLocator;
    }

    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = routeDefinitionLocator.getRouteDefinitions().collectList().block();

        if (definitions != null) {
            definitions.forEach(routeDefinition -> {
                // 获取微服务名称 weilai-wisark-user等
                String group = routeDefinition.getId();
                log.info("对应的id为{}", group);
                // 转换为对应的中文
                // 替换原来的 valueOf 调用
                SwaggerEnum swaggerEnum = SwaggerEnum.fromRouteId(group);
                // 控制文档中文显示
                String groupDesc = null;
                if (swaggerEnum != null) {
                    groupDesc = swaggerEnum.getDesc();
                }
                // 控制访问 /v3/api-docs/weilai-wisark-user
                swaggerUiConfigParameters.addGroup(group);
                groups.add(GroupedOpenApi.builder()
                        // 控制访问 /v3/api-docs/weilai-wisark-user
                        .group(group)
                        .displayName(groupDesc)
                        // 匹配所有接口
                        .pathsToMatch("/**")
                        .build());
            });
        }
        return groups;
    }

}