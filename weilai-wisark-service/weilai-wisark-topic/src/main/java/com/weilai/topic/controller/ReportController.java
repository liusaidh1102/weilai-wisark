package com.weilai.topic.controller;

import com.weilai.common.response.Result;
import com.weilai.model.topic.dos.Report;
import com.weilai.topic.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 举报记录相关接口
 */
@Slf4j
@RestController
@RequestMapping("/report")
@Tag(name = "举报记录模块", description = "举报记录相关接口")
public class ReportController {

    @Resource
    private ReportService reportService;

    /**
     * 获取所有举报记录
     *
     * @return 举报记录列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取举报记录列表", description = "获取所有举报记录")
    public Result<List<Report>> getAllReports() {
        List<Report> reports = reportService.getAllReports();
        return Result.ok(reports);
    }

    /**
     * 根据ID获取举报记录
     *
     * @param id 举报记录ID
     * @return 举报记录详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取举报记录详情", description = "根据ID获取举报记录详情")
    public Result<Report> getReportById(@PathVariable Long id) {
        Report report = reportService.getReportById(id);
        return Result.ok(report);
    }

    /**
     * 添加举报记录
     *
     * @param report 举报记录信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加举报记录", description = "添加新的举报记录")
    public Result<String> addReport(@RequestBody Report report) {
        reportService.addReport(report);
        return Result.ok("举报记录添加成功");
    }

    /**
     * 更新举报记录
     *
     * @param report 举报记录信息
     * @return 更新结果
     */
    @PutMapping("/update")
    @Operation(summary = "更新举报记录", description = "更新举报记录信息")
    public Result<String> updateReport(@RequestBody Report report) {
        reportService.updateReport(report);
        return Result.ok("举报记录更新成功");
    }
}