package com.weilai.topic.service;

import com.weilai.model.topic.dos.Report;

import java.util.List;

/**
 * 举报记录服务接口
 */
public interface ReportService {

    /**
     * 获取所有举报记录
     *
     * @return 举报记录列表
     */
    List<Report> getAllReports();

    /**
     * 根据ID获取举报记录
     *
     * @param id 举报记录ID
     * @return 举报记录详情
     */
    Report getReportById(Long id);

    /**
     * 添加举报记录
     *
     * @param report 举报记录信息
     */
    void addReport(Report report);

    /**
     * 更新举报记录
     *
     * @param report 举报记录信息
     */
    void updateReport(Report report);
}