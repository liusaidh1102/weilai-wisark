package com.weilai.topic.controller;

import com.weilai.common.response.Result;
import com.weilai.model.topic.dos.Comment;
import com.weilai.topic.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论相关接口
 */
@Slf4j
@RestController
@RequestMapping("/comment")
@Tag(name = "评论模块", description = "评论相关接口")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 获取文章的所有评论
     *
     * @param articleId 文章ID
     * @return 评论列表
     */
    @GetMapping("/list/{articleId}")
    @Operation(summary = "获取文章评论列表", description = "根据文章ID获取所有评论")
    public Result<List<Comment>> getCommentsByArticleId(@PathVariable Long articleId) {
        List<Comment> comments = commentService.getCommentsByArticleId(articleId);
        return Result.ok(comments);
    }

    /**
     * 添加评论
     *
     * @param comment 评论信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加评论", description = "添加新的评论")
    public Result<String> addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return Result.ok("评论添加成功");
    }

    /**
     * 删除评论
     *
     * @param id 评论ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除评论", description = "根据评论ID删除评论")
    public Result<String> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return Result.ok("评论删除成功");
    }
}