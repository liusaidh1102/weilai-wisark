package com.weilai.topic.service;

import com.weilai.model.topic.dos.ArticleTag;

import java.util.List;

/**
 * 文章标签关联服务接口
 */
public interface ArticleTagService {

    /**
     * 获取所有文章标签关联
     *
     * @return 文章标签关联列表
     */
    List<ArticleTag> getAllArticleTags();

    /**
     * 根据ID获取文章标签关联
     *
     * @param id 文章标签关联ID
     * @return 文章标签关联详情
     */
    ArticleTag getArticleTagById(Long id);

    /**
     * 添加文章标签关联
     *
     * @param articleTag 文章标签关联信息
     */
    void addOrUpdateArticleTag(ArticleTag articleTag);

    /**
     * 删除文章标签关联
     *
     * @param id 文章标签关联ID
     */
    boolean deleteArticleTag(Long id);

}