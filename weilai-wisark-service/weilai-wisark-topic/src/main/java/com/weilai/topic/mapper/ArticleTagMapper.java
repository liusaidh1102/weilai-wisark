package com.weilai.topic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.topic.dos.ArticleTag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章标签关联Mapper接口
 */
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {
}