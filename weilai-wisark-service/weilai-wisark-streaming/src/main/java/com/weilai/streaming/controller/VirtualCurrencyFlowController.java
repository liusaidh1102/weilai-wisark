package com.weilai.streaming.controller;

import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.VirtualCurrencyFlow;
import com.weilai.streaming.service.VirtualCurrencyFlowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 虚拟货币流水相关接口
 */
@Slf4j
@RestController
@RequestMapping("/virtualCurrencyFlow")
@Tag(name = "虚拟货币流水模块", description = "虚拟货币流水相关接口")
public class VirtualCurrencyFlowController {

    @Resource
    private VirtualCurrencyFlowService virtualCurrencyFlowService;

    /**
     * 获取所有虚拟货币流水
     *
     * @return 虚拟货币流水列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取虚拟货币流水列表", description = "获取所有虚拟货币流水")
    public Result<List<VirtualCurrencyFlow>> getAllVirtualCurrencyFlows() {
        List<VirtualCurrencyFlow> virtualCurrencyFlows = virtualCurrencyFlowService.getAllVirtualCurrencyFlows();
        return Result.ok(virtualCurrencyFlows);
    }

    /**
     * 根据ID获取虚拟货币流水
     *
     * @param id 虚拟货币流水ID
     * @return 虚拟货币流水详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取虚拟货币流水详情", description = "根据ID获取虚拟货币流水详情")
    public Result<VirtualCurrencyFlow> getVirtualCurrencyFlowById(@PathVariable Long id) {
        VirtualCurrencyFlow virtualCurrencyFlow = virtualCurrencyFlowService.getVirtualCurrencyFlowById(id);
        return Result.ok(virtualCurrencyFlow);
    }

    /**
     * 添加虚拟货币流水
     *
     * @param virtualCurrencyFlow 虚拟货币流水信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加虚拟货币流水", description = "添加新的虚拟货币流水")
    public Result<String> addVirtualCurrencyFlow(@RequestBody VirtualCurrencyFlow virtualCurrencyFlow) {
        virtualCurrencyFlowService.addVirtualCurrencyFlow(virtualCurrencyFlow);
        return Result.ok("虚拟货币流水添加成功");
    }
}