package com.weilai.topic.controller;

import com.weilai.common.response.Result;
import com.weilai.model.topic.dos.Follow;
import com.weilai.topic.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 关注记录相关接口
 */
@Slf4j
@RestController
@RequestMapping("/follow")
@Tag(name = "关注记录模块", description = "关注记录相关接口")
public class FollowController {

    @Resource
    private FollowService followService;

    /**
     * 获取所有关注记录
     *
     * @return 关注记录列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取关注记录列表", description = "获取所有关注记录")
    public Result<List<Follow>> getAllFollows() {
        List<Follow> follows = followService.getAllFollows();
        return Result.ok(follows);
    }

    /**
     * 根据ID获取关注记录
     *
     * @param id 关注记录ID
     * @return 关注记录详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取关注记录详情", description = "根据ID获取关注记录详情")
    public Result<Follow> getFollowById(@PathVariable Long id) {
        Follow follow = followService.getFollowById(id);
        return Result.ok(follow);
    }

    /**
     * 添加关注记录
     *
     * @param follow 关注记录信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加关注记录", description = "添加新的关注记录")
    public Result<String> addFollow(@RequestBody Follow follow) {
        followService.addFollow(follow);
        return Result.ok("关注记录添加成功");
    }

    /**
     * 删除关注记录
     *
     * @param id 关注记录ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除关注记录", description = "根据关注记录ID删除关注记录")
    public Result<String> deleteFollow(@PathVariable Long id) {
        followService.deleteFollow(id);
        return Result.ok("关注记录删除成功");
    }
}