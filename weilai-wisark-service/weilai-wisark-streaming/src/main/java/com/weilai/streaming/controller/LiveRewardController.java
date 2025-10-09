package com.weilai.streaming.controller;

import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.LiveReward;
import com.weilai.streaming.service.LiveRewardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 直播打赏相关接口
 */
@Slf4j
@RestController
@RequestMapping("/liveReward")
@Tag(name = "直播打赏模块", description = "直播打赏相关接口")
public class LiveRewardController {

    @Resource
    private LiveRewardService liveRewardService;

    /**
     * 获取所有直播打赏
     *
     * @return 直播打赏列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取直播打赏列表", description = "获取所有直播打赏")
    public Result<List<LiveReward>> getAllLiveRewards() {
        List<LiveReward> liveRewards = liveRewardService.getAllLiveRewards();
        return Result.ok(liveRewards);
    }

    /**
     * 根据ID获取直播打赏
     *
     * @param id 直播打赏ID
     * @return 直播打赏详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取直播打赏详情", description = "根据ID获取直播打赏详情")
    public Result<LiveReward> getLiveRewardById(@PathVariable Long id) {
        LiveReward liveReward = liveRewardService.getLiveRewardById(id);
        return Result.ok(liveReward);
    }

    /**
     * 添加直播打赏
     *
     * @param liveReward 直播打赏信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加直播打赏", description = "添加新的直播打赏")
    public Result<String> addLiveReward(@RequestBody LiveReward liveReward) {
        liveRewardService.addLiveReward(liveReward);
        return Result.ok("直播打赏添加成功");
    }
}