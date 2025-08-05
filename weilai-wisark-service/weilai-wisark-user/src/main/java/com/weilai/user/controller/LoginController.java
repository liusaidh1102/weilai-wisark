package com.weilai.user.controller;
import com.weilai.common.response.Result;
import com.weilai.user.service.UserService;
import com.weilai.model.user.dos.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "", description = "登录接口")
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private ApplicationContext context;





    @GetMapping("/login")
    public Result<User> login() {
        // 打印所有Bean名称，搜索GlobalExceptionHandler
        String[] beanNames = context.getBeanDefinitionNames();
        for (String name : beanNames) {
//            System.out.println(name);
            if (name.contains("globalException")) {
                System.out.println("找到异常处理器Bean：" + name);
            }
        }
        User user = userService.getById(1);
        return Result.ok(user);
    }

    @GetMapping("/test")
    public Result<?> test() {
        return Result.ok();
    }



}
