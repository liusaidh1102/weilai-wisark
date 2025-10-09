package com.weilai.topic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.topic.dos.Like;
import org.apache.ibatis.annotations.Mapper;

/**
 * 点赞记录Mapper接口
 */
@Mapper
public interface LikeMapper extends BaseMapper<Like> {
}