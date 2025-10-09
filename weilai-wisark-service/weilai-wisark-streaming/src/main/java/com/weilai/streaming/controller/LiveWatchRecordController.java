package com.weilai.streaming.controller;

import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.LiveWatchRecord;
import com.weilai.streaming.service.LiveWatchRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 直播观看记录相关接口
 */
@Slf4j
@RestController
@RequestMapping("/liveWatchRecord")
@Tag(name = "直播观看记录模块", description = "直播观看记录相关接口")
public class LiveWatchRecordController {

    @Resource
    private LiveWatchRecordService liveWatchRecordService;

    /**
     * 获取所有直播观看记录
     *
     * @return 直播观看记录列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取直播观看记录列表", description = "获取所有直播观看记录")
    public Result<List<LiveWatchRecord>> getAllLiveWatchRecords() {
        List<LiveWatchRecord> liveWatchRecords = liveWatchRecordService.getAllLiveWatchRecords();
        return Result.ok(liveWatchRecords);
    }

    /**
     * 根据ID获取直播观看记录
     *
     * @param id 直播观看记录ID
     * @return 直播观看记录详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取直播观看记录详情", description = "根据ID获取直播观看记录详情")
    public Result<LiveWatchRecord> getLiveWatchRecordById(@PathVariable Long id) {
        LiveWatchRecord liveWatchRecord = liveWatchRecordService.getLiveWatchRecordById(id);
        return Result.ok(liveWatchRecord);
    }

    /**
     * 添加直播观看记录
     *
     * @param liveWatchRecord 直播观看记录信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加直播观看记录", description = "添加新的直播观看记录")
    public Result<String> addLiveWatchRecord(@RequestBody LiveWatchRecord liveWatchRecord) {
        liveWatchRecordService.addLiveWatchRecord(liveWatchRecord);
        return Result.ok("直播观看记录添加成功");
    }
}