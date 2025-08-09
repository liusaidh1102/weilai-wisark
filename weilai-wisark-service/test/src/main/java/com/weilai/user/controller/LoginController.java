package com.weilai.user.controller;

import com.weilai.common.response.Result;
import com.weilai.user.service.UserService;
import com.weilai.model.user.dos.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

@RestController
@Tag(name = "", description = "测试接口")
@Slf4j
public class LoginController {

    @Resource
    private UserService userService;

    /**
     *  获取网关传来的用户信息
     * @param userId
     * @return
     */
    @GetMapping("/test")
    public Result<?> test(@RequestHeader (value = "userId",required = false) String userId) {
        log.info("userId:{}",userId);
        return Result.ok(userId);
    }



}
