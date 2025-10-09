package com.weilai.streaming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.streaming.dos.LiveReservation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 直播预约Mapper接口
 */
@Mapper
public interface LiveReservationMapper extends BaseMapper<LiveReservation> {
}