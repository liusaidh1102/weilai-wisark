package com.weilai.user.wechat;

import lombok.Data;

@Data
public class TokenInfo {

    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String is_snapshotuser;
    private String unionid;

}
