package com.weilai.common.constants;


/**
 * 缓存常量
 */
public class CacheConstant {

    /*
     * 用户缓存前缀
     */
    public static final String EMAIL_CODE_PREFIX  = "verify:login:code:";
    public static final String LOGIN_TOKEN_PREFIX  = "sa:login:token:";

    public static final long EMAIL_CODE_EXPIRE = 60 * 5;
    public static final long LOGIN_TOKEN_EXPIRE  = 24 * 60 * 60;




}
