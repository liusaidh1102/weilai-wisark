package com.wisark.api.feign.user;
import com.weilai.common.response.Result;
import com.weilai.model.user.vos.UserVO;
import com.wisark.api.config.DefaultConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 用户相关远程调用
 */
// 在这里注入对应的配置
@FeignClient(value = "weilai-wisark-user",configuration = DefaultConfig.class)
public interface UserClient {


    /**
     * 获取当前用户权限
     * @return user:list  user:delete  user:add ....
     */
    @RequestMapping("/user/button/{userId}")
    List<String> getPermissionList(@PathVariable Long userId);


    /**
     * 获取当前用户信息
     * @return  返回当前用户信息
     */
    @GetMapping("/personal/info")
    Result<UserVO> getUserInfo();




}
