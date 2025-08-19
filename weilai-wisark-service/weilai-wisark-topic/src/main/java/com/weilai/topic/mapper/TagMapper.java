package com.weilai.topic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.topic.dos.TagTopic;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签Mapper接口
 */
@Mapper
public interface TagMapper extends BaseMapper<TagTopic> {
}