package com.weilai.topic.controller;

import com.weilai.common.response.Result;
import com.weilai.model.topic.dos.ArticleTag;
import com.weilai.topic.service.ArticleTagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章标签关联相关接口
 */
@Slf4j
@RestController
@RequestMapping("/articleTag")
@Tag(name = "文章标签关联模块", description = "文章标签关联相关接口")
public class ArticleTagController {

    @Resource
    private ArticleTagService articleTagService;

    /**
     * 获取所有文章标签关联
     *
     * @return 文章标签关联列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取文章标签关联列表", description = "获取所有文章标签关联")
    public Result<List<ArticleTag>> getAllArticleTags() {
        List<ArticleTag> articleTags = articleTagService.getAllArticleTags();
        return Result.ok(articleTags);
    }

    /**
     * 根据ID获取文章标签关联
     *
     * @param id 文章标签关联ID
     * @return 文章标签关联详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取文章标签关联详情", description = "根据ID获取文章标签关联详情")
    public Result<ArticleTag> getArticleTagById(@PathVariable Long id) {
        ArticleTag articleTag = articleTagService.getArticleTagById(id);
        return Result.ok(articleTag);
    }

    /**
     * 添加文章标签关联
     *
     * @param articleTag 文章标签关联信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加文章标签关联", description = "添加新的文章标签关联")
    public Result<String> addArticleTag(@RequestBody ArticleTag articleTag) {
        articleTagService.addArticleTag(articleTag);
        return Result.ok("文章标签关联添加成功");
    }

    /**
     * 删除文章标签关联
     *
     * @param id 文章标签关联ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除文章标签关联", description = "根据文章标签关联ID删除文章标签关联")
    public Result<String> deleteArticleTag(@PathVariable Long id) {
        articleTagService.deleteArticleTag(id);
        return Result.ok("文章标签关联删除成功");
    }
}