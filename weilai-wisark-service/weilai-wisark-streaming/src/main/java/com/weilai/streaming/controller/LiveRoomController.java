package com.weilai.streaming.controller;

import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.LiveRoom;
import com.weilai.streaming.service.LiveRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 直播房间相关接口
 */
@Slf4j
@RestController
@RequestMapping("/liveRoom")
@Tag(name = "直播房间模块", description = "直播房间相关接口")
public class LiveRoomController {

    @Resource
    private LiveRoomService liveRoomService;

    /**
     * 获取所有直播房间
     *
     * @return 直播房间列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取直播房间列表", description = "获取所有直播房间")
    public Result<List<LiveRoom>> getAllLiveRooms() {
        List<LiveRoom> liveRooms = liveRoomService.getAllLiveRooms();
        return Result.ok(liveRooms);
    }

    /**
     * 根据ID获取直播房间
     *
     * @param id 直播房间ID
     * @return 直播房间详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取直播房间详情", description = "根据ID获取直播房间详情")
    public Result<LiveRoom> getLiveRoomById(@PathVariable Long id) {
        LiveRoom liveRoom = liveRoomService.getLiveRoomById(id);
        return Result.ok(liveRoom);
    }

    /**
     * 添加直播房间
     *
     * @param liveRoom 直播房间信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加直播房间", description = "添加新的直播房间")
    public Result<String> addLiveRoom(@RequestBody LiveRoom liveRoom) {
        liveRoomService.addLiveRoom(liveRoom);
        return Result.ok("直播房间添加成功");
    }

    /**
     * 更新直播房间
     *
     * @param liveRoom 直播房间信息
     * @return 更新结果
     */
    @PutMapping("/update")
    @Operation(summary = "更新直播房间", description = "更新直播房间信息")
    public Result<String> updateLiveRoom(@RequestBody LiveRoom liveRoom) {
        liveRoomService.updateLiveRoom(liveRoom);
        return Result.ok("直播房间更新成功");
    }

    /**
     * 删除直播房间
     *
     * @param id 直播房间ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除直播房间", description = "根据直播房间ID删除直播房间")
    public Result<String> deleteLiveRoom(@PathVariable Long id) {
        liveRoomService.deleteLiveRoom(id);
        return Result.ok("直播房间删除成功");
    }
}