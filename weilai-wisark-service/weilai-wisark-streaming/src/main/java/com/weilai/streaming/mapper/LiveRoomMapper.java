package com.weilai.streaming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.streaming.dos.LiveRoom;
import org.apache.ibatis.annotations.Mapper;

/**
 * 直播房间Mapper接口
 */
@Mapper
public interface LiveRoomMapper extends BaseMapper<LiveRoom> {
}