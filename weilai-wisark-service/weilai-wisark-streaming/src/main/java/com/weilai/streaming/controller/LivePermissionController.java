package com.weilai.streaming.controller;

import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.LivePermission;
import com.weilai.streaming.service.LivePermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 直播权限相关接口
 */
@Slf4j
@RestController
@RequestMapping("/livePermission")
@Tag(name = "直播权限模块", description = "直播权限相关接口")
public class LivePermissionController {

    @Resource
    private LivePermissionService livePermissionService;

    /**
     * 获取所有直播权限
     *
     * @return 直播权限列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取直播权限列表", description = "获取所有直播权限")
    public Result<List<LivePermission>> getAllLivePermissions() {
        List<LivePermission> livePermissions = livePermissionService.getAllLivePermissions();
        return Result.ok(livePermissions);
    }

    /**
     * 根据ID获取直播权限
     *
     * @param id 直播权限ID
     * @return 直播权限详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取直播权限详情", description = "根据ID获取直播权限详情")
    public Result<LivePermission> getLivePermissionById(@PathVariable Long id) {
        LivePermission livePermission = livePermissionService.getLivePermissionById(id);
        return Result.ok(livePermission);
    }

    /**
     * 添加直播权限
     *
     * @param livePermission 直播权限信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加直播权限", description = "添加新的直播权限")
    public Result<String> addLivePermission(@RequestBody LivePermission livePermission) {
        livePermissionService.addLivePermission(livePermission);
        return Result.ok("直播权限添加成功");
    }

    /**
     * 更新直播权限
     *
     * @param livePermission 直播权限信息
     * @return 更新结果
     */
    @PutMapping("/update")
    @Operation(summary = "更新直播权限", description = "更新直播权限信息")
    public Result<String> updateLivePermission(@RequestBody LivePermission livePermission) {
        livePermissionService.updateLivePermission(livePermission);
        return Result.ok("直播权限更新成功");
    }

    /**
     * 删除直播权限
     *
     * @param id 直播权限ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除直播权限", description = "根据直播权限ID删除直播权限")
    public Result<String> deleteLivePermission(@PathVariable Long id) {
        livePermissionService.deleteLivePermission(id);
        return Result.ok("直播权限删除成功");
    }
}