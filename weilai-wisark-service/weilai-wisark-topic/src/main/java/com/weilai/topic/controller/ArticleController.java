package com.weilai.topic.controller;

import com.weilai.common.response.Result;
import com.weilai.model.topic.dos.Article;
import com.weilai.topic.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章相关接口
 */
@Slf4j
@RestController
@RequestMapping("/article")
@Tag(name = "文章模块", description = "文章相关接口")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * 获取所有文章
     *
     * @return 文章列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取文章列表", description = "获取所有文章")
    public Result<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return Result.ok(articles);
    }

    /**
     * 根据ID获取文章
     *
     * @param id 文章ID
     * @return 文章详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取文章详情", description = "根据ID获取文章详情")
    public Result<Article> getArticleById(@PathVariable Long id) {
        Article article = articleService.getArticleById(id);
        return Result.ok(article);
    }

    /**
     * 添加文章
     *
     * @param article 文章信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加文章", description = "添加新的文章")
    public Result<String> addArticle(@RequestBody Article article) {
        articleService.addArticle(article);
        return Result.ok("文章添加成功");
    }

    /**
     * 更新文章
     *
     * @param article 文章信息
     * @return 更新结果
     */
    @PutMapping("/update")
    @Operation(summary = "更新文章", description = "更新文章信息")
    public Result<String> updateArticle(@RequestBody Article article) {
        articleService.updateArticle(article);
        return Result.ok("文章更新成功");
    }

    /**
     * 删除文章
     *
     * @param id 文章ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除文章", description = "根据文章ID删除文章")
    public Result<String> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return Result.ok("文章删除成功");
    }
}