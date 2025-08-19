package com.weilai.streaming.controller;

import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.SystemNotice;
import com.weilai.streaming.service.SystemNoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统通知相关接口
 */
@Slf4j
@RestController
@RequestMapping("/systemNotice")
@Tag(name = "系统通知模块", description = "系统通知相关接口")
public class SystemNoticeController {

    @Resource
    private SystemNoticeService systemNoticeService;

    /**
     * 获取所有系统通知
     *
     * @return 系统通知列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取系统通知列表", description = "获取所有系统通知")
    public Result<List<SystemNotice>> getAllSystemNotices() {
        List<SystemNotice> systemNotices = systemNoticeService.getAllSystemNotices();
        return Result.ok(systemNotices);
    }

    /**
     * 根据ID获取系统通知
     *
     * @param id 系统通知ID
     * @return 系统通知详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取系统通知详情", description = "根据ID获取系统通知详情")
    public Result<SystemNotice> getSystemNoticeById(@PathVariable Long id) {
        SystemNotice systemNotice = systemNoticeService.getSystemNoticeById(id);
        return Result.ok(systemNotice);
    }

    /**
     * 添加系统通知
     *
     * @param systemNotice 系统通知信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加系统通知", description = "添加新的系统通知")
    public Result<String> addSystemNotice(@RequestBody SystemNotice systemNotice) {
        systemNoticeService.addSystemNotice(systemNotice);
        return Result.ok("系统通知添加成功");
    }

    /**
     * 更新系统通知
     *
     * @param systemNotice 系统通知信息
     * @return 更新结果
     */
    @PutMapping("/update")
    @Operation(summary = "更新系统通知", description = "更新系统通知信息")
    public Result<String> updateSystemNotice(@RequestBody SystemNotice systemNotice) {
        systemNoticeService.updateSystemNotice(systemNotice);
        return Result.ok("系统通知更新成功");
    }

    /**
     * 删除系统通知
     *
     * @param id 系统通知ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除系统通知", description = "根据系统通知ID删除系统通知")
    public Result<String> deleteSystemNotice(@PathVariable Long id) {
        systemNoticeService.deleteSystemNotice(id);
        return Result.ok("系统通知删除成功");
    }
}