package com.weilai.streaming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.streaming.dos.LiveWatchRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * 直播观看记录Mapper接口
 */
@Mapper
public interface LiveWatchRecordMapper extends BaseMapper<LiveWatchRecord> {
}