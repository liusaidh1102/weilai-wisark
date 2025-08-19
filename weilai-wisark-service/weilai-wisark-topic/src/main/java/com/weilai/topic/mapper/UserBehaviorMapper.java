package com.weilai.topic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.topic.dos.UserBehavior;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户行为追踪Mapper接口
 */
@Mapper
public interface UserBehaviorMapper extends BaseMapper<UserBehavior> {
}