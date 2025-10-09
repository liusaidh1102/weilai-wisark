package com.weilai.streaming.controller;

import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.LiveStatistics;
import com.weilai.streaming.service.LiveStatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 直播统计数据相关接口
 */
@Slf4j
@RestController
@RequestMapping("/liveStatistics")
@Tag(name = "直播统计数据模块", description = "直播统计数据相关接口")
public class LiveStatisticsController {

    @Resource
    private LiveStatisticsService liveStatisticsService;

    /**
     * 获取所有直播统计数据
     *
     * @return 直播统计数据列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取直播统计数据列表", description = "获取所有直播统计数据")
    public Result<List<LiveStatistics>> getAllLiveStatistics() {
        List<LiveStatistics> liveStatistics = liveStatisticsService.getAllLiveStatistics();
        return Result.ok(liveStatistics);
    }

    /**
     * 根据ID获取直播统计数据
     *
     * @param id 直播统计数据ID
     * @return 直播统计数据详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取直播统计数据详情", description = "根据ID获取直播统计数据详情")
    public Result<LiveStatistics> getLiveStatisticsById(@PathVariable Long id) {
        LiveStatistics liveStatistics = liveStatisticsService.getLiveStatisticsById(id);
        return Result.ok(liveStatistics);
    }

    /**
     * 添加直播统计数据
     *
     * @param liveStatistics 直播统计数据信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加直播统计数据", description = "添加新的直播统计数据")
    public Result<String> addLiveStatistics(@RequestBody LiveStatistics liveStatistics) {
        liveStatisticsService.addLiveStatistics(liveStatistics);
        return Result.ok("直播统计数据添加成功");
    }

    /**
     * 更新直播统计数据
     *
     * @param liveStatistics 直播统计数据信息
     * @return 更新结果
     */
    @PutMapping("/update")
    @Operation(summary = "更新直播统计数据", description = "更新直播统计数据信息")
    public Result<String> updateLiveStatistics(@RequestBody LiveStatistics liveStatistics) {
        liveStatisticsService.updateLiveStatistics(liveStatistics);
        return Result.ok("直播统计数据更新成功");
    }
}