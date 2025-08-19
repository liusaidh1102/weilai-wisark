package com.weilai.streaming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.streaming.dos.VirtualGift;
import org.apache.ibatis.annotations.Mapper;

/**
 * 虚拟礼物Mapper接口
 */
@Mapper
public interface VirtualGiftMapper extends BaseMapper<VirtualGift> {
}