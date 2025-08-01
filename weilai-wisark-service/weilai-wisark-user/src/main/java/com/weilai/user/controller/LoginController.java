package com.weilai.user.controller;

import com.weilai.common.response.Result;
import com.weilai.user.service.UserService;
import com.weilai.model.user.dos.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "登录接口")
public class LoginController {

    @Resource
    private UserService userService;



    @GetMapping("/login")
    @ApiOperation(value = "登录接口")
    public Result<User> login() {
        User user = userService.getById(1);
        return Result.ok(user);
    }

    @GetMapping("/test")
    @ApiOperation(value = "测试")
    public Result<?> test() {
        return Result.ok();
    }



}
