package com.weilai.topic.controller;

import com.weilai.common.response.Result;
import com.weilai.model.topic.dos.Like;
import com.weilai.topic.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 点赞记录相关接口
 */
@Slf4j
@RestController
@RequestMapping("/like")
@Tag(name = "点赞记录模块", description = "点赞记录相关接口")
public class LikeController {

    @Resource
    private LikeService likeService;

    /**
     * 获取所有点赞记录
     *
     * @return 点赞记录列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取点赞记录列表", description = "获取所有点赞记录")
    public Result<List<Like>> getAllLikes() {
        List<Like> likes = likeService.getAllLikes();
        return Result.ok(likes);
    }

    /**
     * 根据ID获取点赞记录
     *
     * @param id 点赞记录ID
     * @return 点赞记录详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取点赞记录详情", description = "根据ID获取点赞记录详情")
    public Result<Like> getLikeById(@PathVariable Long id) {
        Like like = likeService.getLikeById(id);
        return Result.ok(like);
    }

    /**
     * 添加点赞记录
     *
     * @param like 点赞记录信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加点赞记录", description = "添加新的点赞记录")
    public Result<String> addLike(@RequestBody Like like) {
        likeService.addLike(like);
        return Result.ok("点赞记录添加成功");
    }

    /**
     * 删除点赞记录
     *
     * @param id 点赞记录ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除点赞记录", description = "根据点赞记录ID删除点赞记录")
    public Result<String> deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
        return Result.ok("点赞记录删除成功");
    }
}