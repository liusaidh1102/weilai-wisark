package com.weilai.streaming.controller;

import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.LiveMessage;
import com.weilai.streaming.service.LiveMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 直播消息相关接口
 */
@Slf4j
@RestController
@RequestMapping("/liveMessage")
@Tag(name = "直播消息模块", description = "直播消息相关接口")
public class LiveMessageController {

    @Resource
    private LiveMessageService liveMessageService;

    /**
     * 获取所有直播消息
     *
     * @return 直播消息列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取直播消息列表", description = "获取所有直播消息")
    public Result<List<LiveMessage>> getAllLiveMessages() {
        List<LiveMessage> liveMessages = liveMessageService.getAllLiveMessages();
        return Result.ok(liveMessages);
    }

    /**
     * 根据ID获取直播消息
     *
     * @param id 直播消息ID
     * @return 直播消息详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取直播消息详情", description = "根据ID获取直播消息详情")
    public Result<LiveMessage> getLiveMessageById(@PathVariable Long id) {
        LiveMessage liveMessage = liveMessageService.getLiveMessageById(id);
        return Result.ok(liveMessage);
    }

    /**
     * 添加直播消息
     *
     * @param liveMessage 直播消息信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加直播消息", description = "添加新的直播消息")
    public Result<String> addLiveMessage(@RequestBody LiveMessage liveMessage) {
        liveMessageService.addLiveMessage(liveMessage);
        return Result.ok("直播消息添加成功");
    }

    /**
     * 删除直播消息
     *
     * @param id 直播消息ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除直播消息", description = "根据直播消息ID删除直播消息")
    public Result<String> deleteLiveMessage(@PathVariable Long id) {
        liveMessageService.deleteLiveMessage(id);
        return Result.ok("直播消息删除成功");
    }
}