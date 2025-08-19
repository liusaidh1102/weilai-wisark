package com.weilai.streaming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.streaming.dos.LiveAllowUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 直播允许用户Mapper接口
 */
@Mapper
public interface LiveAllowUserMapper extends BaseMapper<LiveAllowUser> {
}