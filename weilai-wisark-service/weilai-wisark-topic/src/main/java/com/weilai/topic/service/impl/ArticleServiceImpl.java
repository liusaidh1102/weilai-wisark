package com.weilai.topic.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.topic.dos.*;
import com.weilai.model.topic.dtos.PublishArticleDTO;
import com.weilai.model.topic.vos.ArticleVO;
import com.weilai.topic.mapper.ArticleMapper;
import com.weilai.topic.service.*;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 文章服务实现类
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private TagService tagService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private ArticleTagService articleTagService;

    @Resource
    private ArticleCategoryService articleCategoryService;

    @Override
    public List<Article> getAllArticles() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("deleted_at")
                .orderByDesc("created_at");
        return articleMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleVO getArticleById(Long id) {
        ArticleVO articleVO = new ArticleVO();
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .isNull("deleted_at");
        Article article = articleMapper.selectOne(queryWrapper);
        if (article != null) {
            BeanUtils.copyProperties(articleVO, article);
        }
        //处理文章标签关联
        ArticleTag articleTag = articleTagService.getArticleTagById(id);
        if (articleTag != null) {
            TagTopic tagTopic = tagService.getTagById(articleTag.getTagId());
            articleVO.setTagName(tagTopic.getName());
        }
        //处理文章分类关联
        ArticleCategory articleCategory = articleCategoryService.getArticleCategoryById(id);
        if (articleCategory != null) {
            Category category = categoryService.getCategoryById(articleCategory.getCategoryId());
            articleVO.setCategoryName(category.getName());
        }
        return articleVO;
    }

    @Override
    /**
     * 发布文章
     * @param publishArticleDTO 发布文章的数据传输对象
     * @return 发布后的文章ID
     */
    @Transactional(rollbackFor = Exception.class)
    public Long addArticle(PublishArticleDTO articleDTO) {
        // 创建文章实体
        Article article=new Article();
        BeanUtils.copyProperties(article, articleDTO);
        article.setCreatedAt(new DateTime());
        article.setUpdatedAt(new DateTime());
        boolean  flag =  save(article);
        // 处理文章标签关联
        handleArticleTags(article.getId(), articleDTO.getTagId());
        // 处理文章分类关联
        handleArticleCategories(article.getId(), articleDTO.getCategoryId());
        return flag ? article.getId() : null;
    }

    @Override
    public void updateArticle(PublishArticleDTO articleDTO) {
        // 创建文章实体
        Article article=new Article();
        BeanUtils.copyProperties(article, articleDTO);
        article.setUpdatedAt(new DateTime());
        // 处理文章标签关联
        handleArticleTags(article.getId(), articleDTO.getTagId());
        // 处理文章分类关联
        handleArticleCategories(article.getId(), articleDTO.getCategoryId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteArticle(Long id) {
        Article article = new Article();
        article.setId(id);
        // 软删除，设置删除时间
        article.setDeletedAt(new DateTime());
        articleMapper.updateById(article);
        // 删除文章标签关联
        articleTagService.deleteArticleTag(id);
        // 删除文章分类关联
        articleCategoryService.deleteArticleCategory(id);
    }

    /**
     * 处理文章标签关联
     * @param articleId 文章ID
     * @param tagId 标签名称列表（逗号分隔）
     */
    private void handleArticleTags(Long articleId, String tagId) {
        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticleId(articleId);
        articleTag.setTagId(Long.parseLong(tagId));
        articleTag.setCreatedAt(new DateTime());
        articleTagService.addOrUpdateArticleTag(articleTag);
    }

    /**
     * 处理文章分类关联
     * @param articleId 文章ID
     * @param categoryId 分类名称列表（逗号分隔）
     */
    private void handleArticleCategories(Long articleId, String categoryId) {
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setArticleId(articleId);
        articleCategory.setCategoryId(Long.parseLong(categoryId));
        articleCategory.setCreatedAt(new DateTime());
        articleCategoryService.addOrUpdateArticleCategory(articleCategory);
    }
}