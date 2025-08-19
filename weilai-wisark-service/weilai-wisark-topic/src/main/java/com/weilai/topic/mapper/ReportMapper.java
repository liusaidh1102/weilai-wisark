package com.weilai.topic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.topic.dos.Report;
import org.apache.ibatis.annotations.Mapper;

/**
 * 举报记录Mapper接口
 */
@Mapper
public interface ReportMapper extends BaseMapper<Report> {
}