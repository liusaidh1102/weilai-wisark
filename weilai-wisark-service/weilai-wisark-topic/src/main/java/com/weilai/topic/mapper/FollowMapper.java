package com.weilai.topic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.topic.dos.Follow;
import org.apache.ibatis.annotations.Mapper;

/**
 * 关注记录Mapper接口
 */
@Mapper
public interface FollowMapper extends BaseMapper<Follow> {
}