package com.weilai.streaming.controller;

import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.LiveReservation;
import com.weilai.streaming.service.LiveReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 直播预约相关接口
 */
@Slf4j
@RestController
@RequestMapping("/liveReservation")
@Tag(name = "直播预约模块", description = "直播预约相关接口")
public class LiveReservationController {

    @Resource
    private LiveReservationService liveReservationService;

    /**
     * 获取所有直播预约
     *
     * @return 直播预约列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取直播预约列表", description = "获取所有直播预约")
    public Result<List<LiveReservation>> getAllLiveReservations() {
        List<LiveReservation> liveReservations = liveReservationService.getAllLiveReservations();
        return Result.ok(liveReservations);
    }

    /**
     * 根据ID获取直播预约
     *
     * @param id 直播预约ID
     * @return 直播预约详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取直播预约详情", description = "根据ID获取直播预约详情")
    public Result<LiveReservation> getLiveReservationById(@PathVariable Long id) {
        LiveReservation liveReservation = liveReservationService.getLiveReservationById(id);
        return Result.ok(liveReservation);
    }

    /**
     * 添加直播预约
     *
     * @param liveReservation 直播预约信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加直播预约", description = "添加新的直播预约")
    public Result<String> addLiveReservation(@RequestBody LiveReservation liveReservation) {
        liveReservationService.addLiveReservation(liveReservation);
        return Result.ok("直播预约添加成功");
    }

    /**
     * 删除直播预约
     *
     * @param id 直播预约ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除直播预约", description = "根据直播预约ID删除直播预约")
    public Result<String> deleteLiveReservation(@PathVariable Long id) {
        liveReservationService.deleteLiveReservation(id);
        return Result.ok("直播预约删除成功");
    }
}