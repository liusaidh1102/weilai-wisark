package com.weilai.streaming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.streaming.dos.LiveStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * 直播统计数据Mapper接口
 */
@Mapper
public interface LiveStatisticsMapper extends BaseMapper<LiveStatistics> {
}