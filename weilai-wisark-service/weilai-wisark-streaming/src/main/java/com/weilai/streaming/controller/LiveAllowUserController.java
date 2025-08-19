package com.weilai.streaming.controller;

import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.LiveAllowUser;
import com.weilai.streaming.service.LiveAllowUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 直播允许用户相关接口
 */
@Slf4j
@RestController
@RequestMapping("/liveAllowUser")
@Tag(name = "直播允许用户模块", description = "直播允许用户相关接口")
public class LiveAllowUserController {

    @Resource
    private LiveAllowUserService liveAllowUserService;

    /**
     * 获取所有直播允许用户
     *
     * @return 直播允许用户列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取直播允许用户列表", description = "获取所有直播允许用户")
    public Result<List<LiveAllowUser>> getAllLiveAllowUsers() {
        List<LiveAllowUser> liveAllowUsers = liveAllowUserService.getAllLiveAllowUsers();
        return Result.ok(liveAllowUsers);
    }

    /**
     * 根据ID获取直播允许用户
     *
     * @param id 直播允许用户ID
     * @return 直播允许用户详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取直播允许用户详情", description = "根据ID获取直播允许用户详情")
    public Result<LiveAllowUser> getLiveAllowUserById(@PathVariable Long id) {
        LiveAllowUser liveAllowUser = liveAllowUserService.getLiveAllowUserById(id);
        return Result.ok(liveAllowUser);
    }

    /**
     * 添加直播允许用户
     *
     * @param liveAllowUser 直播允许用户信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加直播允许用户", description = "添加新的直播允许用户")
    public Result<String> addLiveAllowUser(@RequestBody LiveAllowUser liveAllowUser) {
        liveAllowUserService.addLiveAllowUser(liveAllowUser);
        return Result.ok("直播允许用户添加成功");
    }

    /**
     * 删除直播允许用户
     *
     * @param id 直播允许用户ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除直播允许用户", description = "根据直播允许用户ID删除直播允许用户")
    public Result<String> deleteLiveAllowUser(@PathVariable Long id) {
        liveAllowUserService.deleteLiveAllowUser(id);
        return Result.ok("直播允许用户删除成功");
    }
}