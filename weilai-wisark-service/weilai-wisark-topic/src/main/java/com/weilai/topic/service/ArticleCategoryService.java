package com.weilai.topic.service;

import com.weilai.model.topic.dos.ArticleCategory;

/**
 * 分类与文章关联服务
 */
public interface ArticleCategoryService {
    /**
     * 添加分类与文章关联
     * @param articleCategory
     */
    void addOrUpdateArticleCategory(ArticleCategory articleCategory);

    /**
     * 删除分类与文章关联
     * @param articleId
     *
     */
    boolean deleteArticleCategory(Long articleId);

    /**
     * 根据ID获取分类与文章关联
     * @param id
     * @return
     */
    ArticleCategory getArticleCategoryById(Long id);
}
