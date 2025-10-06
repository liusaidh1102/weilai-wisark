package com.weilai.user.controller;
import cn.dev33.satoken.stp.StpUtil;
import com.weilai.common.response.Result;
import com.weilai.model.user.vos.UserVO;
import com.weilai.user.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import static com.weilai.common.response.CodeEnum.USER_NOT_EXISTS;
/**
 * 用户个人信息模块
 */
@RestController
@RequestMapping("/personal")
@Tag(name = "用户个人中心", description = "用户个人中心")
public class PersonalController {


    @Resource
    private UserService userService;

    @GetMapping("/info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "603", description = "用户不存在")
    })
    public Result<UserVO> info() {
        String userIdStr = (String) StpUtil.getLoginId();
        Long userId = Long.parseLong(userIdStr);
        UserVO userVO = userService.getUserVO(userId);
        if(userVO == null){
            return Result.fail(USER_NOT_EXISTS);
        }
        return Result.ok(userVO);
    }

}
