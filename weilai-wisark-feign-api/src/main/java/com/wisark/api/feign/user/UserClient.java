package com.wisark.api.feign.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 用户相关远程调用
 */
@FeignClient("weilai-wisark-user")
public interface UserClient {


    /**
     * 获取当前用户权限
     * @return user:list  user:delete  user:add ....
     */
    @RequestMapping("/user/button/{userId}")
    List<String> getPermissionList(@PathVariable Long userId);



}
