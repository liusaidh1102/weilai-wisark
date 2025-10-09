package com.weilai.topic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.topic.dos.ArticleCategory;
import com.weilai.topic.mapper.ArticleCategoryMapper;
import com.weilai.topic.service.ArticleCategoryService;
import org.springframework.stereotype.Service;

@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements ArticleCategoryService {
    @Override
    public void addOrUpdateArticleCategory(ArticleCategory articleCategory) {
        saveOrUpdate(articleCategory);
    }

    @Override
    public boolean deleteArticleCategory(Long articleId) {
        QueryWrapper<ArticleCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
                return remove(queryWrapper);
    }

    @Override
    public ArticleCategory getArticleCategoryById(Long id) {
        return getById(id);
    }
}
