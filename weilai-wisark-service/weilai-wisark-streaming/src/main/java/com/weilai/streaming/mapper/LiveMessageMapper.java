package com.weilai.streaming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.streaming.dos.LiveMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * 直播消息Mapper接口
 */
@Mapper
public interface LiveMessageMapper extends BaseMapper<LiveMessage> {
}