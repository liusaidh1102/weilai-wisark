package com.weilai.streaming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.streaming.dos.LiveAudit;
import org.apache.ibatis.annotations.Mapper;

/**
 * 直播审核Mapper接口
 */
@Mapper
public interface LiveAuditMapper extends BaseMapper<LiveAudit> {
}