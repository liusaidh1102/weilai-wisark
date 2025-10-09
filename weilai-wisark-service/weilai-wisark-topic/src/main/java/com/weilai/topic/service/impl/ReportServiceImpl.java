package com.weilai.topic.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.topic.dos.Report;
import com.weilai.topic.mapper.ReportMapper;
import com.weilai.topic.service.ReportService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 举报记录服务实现类
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {

    @Resource
    private ReportMapper reportMapper;

    @Override
    public List<Report> getAllReports() {
        QueryWrapper<Report> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_at");
        return reportMapper.selectList(queryWrapper);
    }

    @Override
    public Report getReportById(Long id) {
        return reportMapper.selectById(id);
    }

    @Override
    public void addReport(Report report) {
        reportMapper.insert(report);
    }

    @Override
    public void updateReport(Report report) {
        report.setUpdatedAt(new DateTime());
        reportMapper.updateById(report);
    }
}