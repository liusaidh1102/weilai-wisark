package com.weilai.common.constants;


/**
 * 缓存常量
 */
public class CacheConstant {

    /*
     * 用户缓存前缀
     */
    // 邮箱验证码缓存key
    public static final String EMAIL_CODE_PREFIX  = "verify:login:code:";
    public static final long EMAIL_CODE_EXPIRE = 60 * 5;
    //  登录token缓存key
    public static final String LOGIN_TOKEN_PREFIX  = "sa:login:token:";
    public static final long LOGIN_TOKEN_EXPIRE  = 24 * 60 * 60;
    // 用户信息缓存key
    public static final String USER_INFO_PREFIX = "user:info:";
    public static final long USER_INFO_EXPIRE = 24 * 60 * 60;
    // 用户权限缓存key
    public static final String USER_PERMISSIONS_PREFIX = "user:permissions:list:";
    public static final long USER_PERMISSIONS_EXPIRE = 12 * 60 * 60;
    // 用户角色缓存key
    public static final String USER_ROLES_PREFIX = "user:roles:list:";
    public static final long USER_ROLES_EXPIRE = 24 * 60 * 60;
    // 用户菜单缓存key
    public static final String USER_MENUS_PREFIX = "user:menu:list:";
    public static final long USER_MENUS_EXPIRE = 12 * 60 * 60;


    /**
     * 文件缓存前缀
     */
    // 文件信息缓存key
    public static final String FILE_INFO_PREFIX = "file:info:";
    public static final int FILE_INFO_EXPIRE = 24 * 60 * 60;


    /**
     * 分布式锁缓存key
     */
    // 文件合并分布式锁
    public static final String LOCK_FILE_KEY_PREFIX = "lock:file:";


    /**
     * ai会话缓存前缀
     */
    public static final String AI_CONVERSATION_PREFIX = "ai:conversation:";
    public static final int AI_CONVERSATION_EXPIRE = 24 * 60 * 60;



}
