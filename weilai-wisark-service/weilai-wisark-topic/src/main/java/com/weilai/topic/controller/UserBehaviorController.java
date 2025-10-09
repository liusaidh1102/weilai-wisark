package com.weilai.topic.controller;

import com.weilai.common.response.Result;
import com.weilai.model.topic.dos.UserBehavior;
import com.weilai.topic.service.UserBehaviorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户行为追踪相关接口
 */
@Slf4j
@RestController
@RequestMapping("/userBehavior")
@Tag(name = "用户行为追踪模块", description = "用户行为追踪相关接口")
public class UserBehaviorController {

    @Resource
    private UserBehaviorService userBehaviorService;

    /**
     * 获取所有用户行为追踪记录
     *
     * @return 用户行为追踪记录列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取用户行为追踪记录列表", description = "获取所有用户行为追踪记录")
    public Result<List<UserBehavior>> getAllUserBehaviors() {
        List<UserBehavior> userBehaviors = userBehaviorService.getAllUserBehaviors();
        return Result.ok(userBehaviors);
    }

    /**
     * 根据ID获取用户行为追踪记录
     *
     * @param id 用户行为追踪记录ID
     * @return 用户行为追踪记录详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取用户行为追踪记录详情", description = "根据ID获取用户行为追踪记录详情")
    public Result<UserBehavior> getUserBehaviorById(@PathVariable Long id) {
        UserBehavior userBehavior = userBehaviorService.getUserBehaviorById(id);
        return Result.ok(userBehavior);
    }

    /**
     * 添加用户行为追踪记录
     *
     * @param userBehavior 用户行为追踪记录信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加用户行为追踪记录", description = "添加新的用户行为追踪记录")
    public Result<String> addUserBehavior(@RequestBody UserBehavior userBehavior) {
        userBehaviorService.addUserBehavior(userBehavior);
        return Result.ok("用户行为追踪记录添加成功");
    }
}