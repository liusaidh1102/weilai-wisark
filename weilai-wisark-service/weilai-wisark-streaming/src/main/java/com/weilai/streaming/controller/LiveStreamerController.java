package com.weilai.streaming.controller;

import com.weilai.common.response.Result;
import com.weilai.streaming.service.LiveStreamerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/liveStreamer")
@Tag(name = "直播流媒体模块", description = "直播流媒体相关接口")
public class LiveStreamerController {
    @Resource
    private LiveStreamerService liveStreamerService;
    @PutMapping("/startLive/{roomId}")
    public Result<String> startLive(@PathVariable String roomId) {
        liveStreamerService.startLive(roomId);
        return Result.ok("直播开始");
    }
    @PutMapping("/stopLive/{roomId}")
    public Result<String> stopLive(@PathVariable String roomId, String replayUrl) {
        liveStreamerService.stopLive(roomId, replayUrl);
        return Result.ok("直播结束");
    }
}
