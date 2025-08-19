package com.weilai.user.wechat;
import lombok.Data;

@Data
public class WechatUser {
    private String nickname;
    private String sex;
    private String headimgurl;
    private String province;
    private String city;
    private String country;
    private String privilege;
    private String unionid;
    private String openid;
}
