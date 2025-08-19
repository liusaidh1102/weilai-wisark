package com.weilai.topic.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.topic.dos.ArticleTag;
import com.weilai.topic.mapper.ArticleTagMapper;
import com.weilai.topic.service.ArticleTagService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章标签关联服务实现类
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

    @Resource
    private ArticleTagMapper articleTagMapper;

    @Override
    public List<ArticleTag> getAllArticleTags() {
        QueryWrapper<ArticleTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_at");
        return articleTagMapper.selectList(queryWrapper);
    }

    @Override
    public ArticleTag getArticleTagById(Long id) {
        return articleTagMapper.selectById(id);
    }

    @Override
    public void addArticleTag(ArticleTag articleTag) {
        articleTagMapper.insert(articleTag);
    }

    @Override
    public void deleteArticleTag(Long id) {
        articleTagMapper.deleteById(id);
    }
}