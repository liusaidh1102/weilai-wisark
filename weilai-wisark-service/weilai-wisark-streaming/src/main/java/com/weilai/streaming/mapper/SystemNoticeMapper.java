package com.weilai.streaming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.streaming.dos.SystemNotice;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统通知Mapper接口
 */
@Mapper
public interface SystemNoticeMapper extends BaseMapper<SystemNotice> {
}