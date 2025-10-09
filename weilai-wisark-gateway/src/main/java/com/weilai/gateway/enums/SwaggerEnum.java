package com.weilai.gateway.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * Swagger配置枚举类
 */
@Getter
public enum SwaggerEnum {

    /**
     * 用户服务API
     */
    USER_SERVICE("weilai-wisark-user", "用户服务接口", "用户相关功能接口"),


    /**
     * AI服务API
     */
    AI_SERVICE("weilai-wisark-ai", "AI服务接口", "人工智能相关功能接口"),

    /**
     * 网关服务API
     */
    GATEWAY_SERVICE("weilai-wisark-gateway", "网关服务接口", "API网关相关功能接口"),
    /**
     * 话题交流的服务API
     */
    TOPIC_SERVICE("weilai-wisark-topic", "话题交流服务接口", "话题交流相关功能接口");

    private final String id;
    private final String title;
    private final String desc;

    SwaggerEnum(String id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
    }

    /**
     * 根据路由ID查找对应的枚举值
     * @param routeId 路由ID
     * @return 对应的枚举值，找不到则返回null
     */
    public static SwaggerEnum fromRouteId(String routeId) {
        return Arrays.stream(values())
                .filter(swagger -> swagger.getId().equals(routeId))
                .findFirst()
                .orElse(null);
    }



}
