package com.weilai.topic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.topic.dos.CollectionTopic;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章收藏Mapper接口
 */
@Mapper
public interface CollectionMapper extends BaseMapper<CollectionTopic> {
}