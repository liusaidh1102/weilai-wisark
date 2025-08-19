package com.weilai.streaming.controller;

import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.LiveTag;
import com.weilai.streaming.service.LiveTagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 直播标签相关接口
 */
@Slf4j
@RestController
@RequestMapping("/liveTag")
@Tag(name = "直播标签模块", description = "直播标签相关接口")
public class LiveTagController {

    @Resource
    private LiveTagService liveTagService;

    /**
     * 获取所有直播标签
     *
     * @return 直播标签列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取直播标签列表", description = "获取所有直播标签")
    public Result<List<LiveTag>> getAllLiveTags() {
        List<LiveTag> liveTags = liveTagService.getAllLiveTags();
        return Result.ok(liveTags);
    }

    /**
     * 根据ID获取直播标签
     *
     * @param id 直播标签ID
     * @return 直播标签详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取直播标签详情", description = "根据ID获取直播标签详情")
    public Result<LiveTag> getLiveTagById(@PathVariable Long id) {
        LiveTag liveTag = liveTagService.getLiveTagById(id);
        return Result.ok(liveTag);
    }

    /**
     * 添加直播标签
     *
     * @param liveTag 直播标签信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加直播标签", description = "添加新的直播标签")
    public Result<String> addLiveTag(@RequestBody LiveTag liveTag) {
        liveTagService.addLiveTag(liveTag);
        return Result.ok("直播标签添加成功");
    }

    /**
     * 删除直播标签
     *
     * @param id 直播标签ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除直播标签", description = "根据直播标签ID删除直播标签")
    public Result<String> deleteLiveTag(@PathVariable Long id) {
        liveTagService.deleteLiveTag(id);
        return Result.ok("直播标签删除成功");
    }
}