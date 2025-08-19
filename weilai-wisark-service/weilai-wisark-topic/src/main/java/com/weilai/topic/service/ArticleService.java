package com.weilai.topic.service;

import com.weilai.model.topic.dos.Article;

import java.util.List;

/**
 * 文章服务接口
 */
public interface ArticleService {

    /**
     * 获取所有文章
     *
     * @return 文章列表
     */
    List<Article> getAllArticles();

    /**
     * 根据ID获取文章
     *
     * @param id 文章ID
     * @return 文章详情
     */
    Article getArticleById(Long id);

    /**
     * 添加文章
     *
     * @param article 文章信息
     */
    void addArticle(Article article);

    /**
     * 更新文章
     *
     * @param article 文章信息
     */
    void updateArticle(Article article);

    /**
     * 删除文章
     *
     * @param id 文章ID
     */
    void deleteArticle(Long id);
}