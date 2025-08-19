package com.weilai.streaming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.streaming.dos.LiveReward;
import org.apache.ibatis.annotations.Mapper;

/**
 * 直播打赏Mapper接口
 */
@Mapper
public interface LiveRewardMapper extends BaseMapper<LiveReward> {
}