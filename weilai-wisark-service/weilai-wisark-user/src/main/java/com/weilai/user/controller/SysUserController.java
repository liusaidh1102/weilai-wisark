package com.weilai.user.controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/sys/user")
@Tag(name = "用户管理", description = "用户管理、管理员管理、在线用户等")
public class SysUserController {

}