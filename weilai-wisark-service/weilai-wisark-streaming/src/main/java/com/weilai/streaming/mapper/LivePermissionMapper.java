package com.weilai.streaming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.streaming.dos.LivePermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 直播权限Mapper接口
 */
@Mapper
public interface LivePermissionMapper extends BaseMapper<LivePermission> {
}