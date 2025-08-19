package com.weilai.topic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.topic.dos.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章Mapper接口
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}