package com.weilai.topic.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.topic.dos.Article;
import com.weilai.topic.mapper.ArticleMapper;
import com.weilai.topic.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章服务实现类
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public List<Article> getAllArticles() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("deleted_at")
                .orderByDesc("created_at");
        return articleMapper.selectList(queryWrapper);
    }

    @Override
    public Article getArticleById(Long id) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .isNull("deleted_at");
        return articleMapper.selectOne(queryWrapper);
    }

    @Override
    public void addArticle(Article article) {
        articleMapper.insert(article);
    }

    @Override
    public void updateArticle(Article article) {
        article.setUpdatedAt(new DateTime());
        articleMapper.updateById(article);
    }

    @Override
    public void deleteArticle(Long id) {
        Article article = new Article();
        article.setId(id);
        // 软删除，设置删除时间
        article.setDeletedAt(new DateTime());
        articleMapper.updateById(article);
    }
}