package com.weilai.gateway.service;
import cn.dev33.satoken.stp.StpInterface;
import com.wisark.api.feign.user.UserClient;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;

/**
 *  获取用户权限信息，便于注解鉴权
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Resource
    private UserClient userClient;

    /**
     * 获取用户权限列表
     * @param loginId 用户ID
     * @param loginType 登录类型
     * @return 权限列表
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return userClient.getPermissionList(Long.valueOf((String) loginId));
    }

    /**
     * 获取用户角色列表
     * @param loginId 用户ID
     * @param loginType 登录类型
     * @return 角色列表
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return Collections.emptyList();
    }

}

