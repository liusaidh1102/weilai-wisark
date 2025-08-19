package com.weilai.streaming.controller;

import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.VirtualGift;
import com.weilai.streaming.service.VirtualGiftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 虚拟礼物相关接口
 */
@Slf4j
@RestController
@RequestMapping("/virtualGift")
@Tag(name = "虚拟礼物模块", description = "虚拟礼物相关接口")
public class VirtualGiftController {

    @Resource
    private VirtualGiftService virtualGiftService;

    /**
     * 获取所有虚拟礼物
     *
     * @return 虚拟礼物列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取虚拟礼物列表", description = "获取所有虚拟礼物")
    public Result<List<VirtualGift>> getAllVirtualGifts() {
        List<VirtualGift> virtualGifts = virtualGiftService.getAllVirtualGifts();
        return Result.ok(virtualGifts);
    }

    /**
     * 根据ID获取虚拟礼物
     *
     * @param id 虚拟礼物ID
     * @return 虚拟礼物详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取虚拟礼物详情", description = "根据ID获取虚拟礼物详情")
    public Result<VirtualGift> getVirtualGiftById(@PathVariable Long id) {
        VirtualGift virtualGift = virtualGiftService.getVirtualGiftById(id);
        return Result.ok(virtualGift);
    }

    /**
     * 添加虚拟礼物
     *
     * @param virtualGift 虚拟礼物信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加虚拟礼物", description = "添加新的虚拟礼物")
    public Result<String> addVirtualGift(@RequestBody VirtualGift virtualGift) {
        virtualGiftService.addVirtualGift(virtualGift);
        return Result.ok("虚拟礼物添加成功");
    }

    /**
     * 更新虚拟礼物
     *
     * @param virtualGift 虚拟礼物信息
     * @return 更新结果
     */
    @PutMapping("/update")
    @Operation(summary = "更新虚拟礼物", description = "更新虚拟礼物信息")
    public Result<String> updateVirtualGift(@RequestBody VirtualGift virtualGift) {
        virtualGiftService.updateVirtualGift(virtualGift);
        return Result.ok("虚拟礼物更新成功");
    }

    /**
     * 删除虚拟礼物
     *
     * @param id 虚拟礼物ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除虚拟礼物", description = "根据虚拟礼物ID删除虚拟礼物")
    public Result<String> deleteVirtualGift(@PathVariable Long id) {
        virtualGiftService.deleteVirtualGift(id);
        return Result.ok("虚拟礼物删除成功");
    }
}