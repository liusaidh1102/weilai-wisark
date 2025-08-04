package com.weilai.user.controller;

import com.weilai.common.response.Result;
import com.weilai.user.service.UserService;
import com.weilai.model.user.dos.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Tag(name = "", description = "登录接口")
public class LoginController {

    @Resource
    private UserService userService;



    @GetMapping("/login")
    public Result<User> login() {
        User user = userService.getById(1);
        return Result.ok(user);
    }

    @GetMapping("/test")
    public Result<?> test() {
        return Result.ok();
    }



}
