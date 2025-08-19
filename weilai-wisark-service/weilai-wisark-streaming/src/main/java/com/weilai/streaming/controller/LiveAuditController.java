package com.weilai.streaming.controller;

import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.LiveAudit;
import com.weilai.streaming.service.LiveAuditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 直播审核相关接口
 */
@Slf4j
@RestController
@RequestMapping("/liveAudit")
@Tag(name = "直播审核模块", description = "直播审核相关接口")
public class LiveAuditController {

    @Resource
    private LiveAuditService liveAuditService;

    /**
     * 获取所有直播审核
     *
     * @return 直播审核列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取直播审核列表", description = "获取所有直播审核")
    public Result<List<LiveAudit>> getAllLiveAudits() {
        List<LiveAudit> liveAudits = liveAuditService.getAllLiveAudits();
        return Result.ok(liveAudits);
    }

    /**
     * 根据ID获取直播审核
     *
     * @param id 直播审核ID
     * @return 直播审核详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取直播审核详情", description = "根据ID获取直播审核详情")
    public Result<LiveAudit> getLiveAuditById(@PathVariable Long id) {
        LiveAudit liveAudit = liveAuditService.getLiveAuditById(id);
        return Result.ok(liveAudit);
    }

    /**
     * 添加直播审核
     *
     * @param liveAudit 直播审核信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加直播审核", description = "添加新的直播审核")
    public Result<String> addLiveAudit(@RequestBody LiveAudit liveAudit) {
        liveAuditService.addLiveAudit(liveAudit);
        return Result.ok("直播审核添加成功");
    }

    /**
     * 更新直播审核
     *
     * @param liveAudit 直播审核信息
     * @return 更新结果
     */
    @PutMapping("/update")
    @Operation(summary = "更新直播审核", description = "更新直播审核信息")
    public Result<String> updateLiveAudit(@RequestBody LiveAudit liveAudit) {
        liveAuditService.updateLiveAudit(liveAudit);
        return Result.ok("直播审核更新成功");
    }
}