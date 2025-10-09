package com.weilai.streaming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.streaming.dos.LiveTag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 直播标签Mapper接口
 */
@Mapper
public interface LiveTagMapper extends BaseMapper<LiveTag> {
}