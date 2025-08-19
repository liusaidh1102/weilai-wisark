package com.weilai.user.wechat;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * 一些配置信息
 */
@Component
@ConfigurationProperties(prefix = "wechat")
@Data
public class WechatApp {

    private String appId;

    private String redirectUri;

    private String scope;

    private String responseType;

    private String appSecret;

    private String token;

}
