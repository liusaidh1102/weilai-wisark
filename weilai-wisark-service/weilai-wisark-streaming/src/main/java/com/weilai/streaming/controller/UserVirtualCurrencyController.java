package com.weilai.streaming.controller;

import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.UserVirtualCurrency;
import com.weilai.streaming.service.UserVirtualCurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户虚拟货币相关接口
 */
@Slf4j
@RestController
@RequestMapping("/userVirtualCurrency")
@Tag(name = "用户虚拟货币模块", description = "用户虚拟货币相关接口")
public class UserVirtualCurrencyController {

    @Resource
    private UserVirtualCurrencyService userVirtualCurrencyService;

    /**
     * 获取所有用户虚拟货币信息
     *
     * @return 用户虚拟货币信息列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取用户虚拟货币信息列表", description = "获取所有用户虚拟货币信息")
    public Result<List<UserVirtualCurrency>> getAllUserVirtualCurrencies() {
        List<UserVirtualCurrency> userVirtualCurrencies = userVirtualCurrencyService.getAllUserVirtualCurrencies();
        return Result.ok(userVirtualCurrencies);
    }

    /**
     * 根据ID获取用户虚拟货币信息
     *
     * @param id 用户虚拟货币信息ID
     * @return 用户虚拟货币信息详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取用户虚拟货币信息详情", description = "根据ID获取用户虚拟货币信息详情")
    public Result<UserVirtualCurrency> getUserVirtualCurrencyById(@PathVariable Long id) {
        UserVirtualCurrency userVirtualCurrency = userVirtualCurrencyService.getUserVirtualCurrencyById(id);
        return Result.ok(userVirtualCurrency);
    }

    /**
     * 添加用户虚拟货币信息
     *
     * @param userVirtualCurrency 用户虚拟货币信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加用户虚拟货币信息", description = "添加新的用户虚拟货币信息")
    public Result<String> addUserVirtualCurrency(@RequestBody UserVirtualCurrency userVirtualCurrency) {
        userVirtualCurrencyService.addUserVirtualCurrency(userVirtualCurrency);
        return Result.ok("用户虚拟货币信息添加成功");
    }

    /**
     * 更新用户虚拟货币信息
     *
     * @param userVirtualCurrency 用户虚拟货币信息
     * @return 更新结果
     */
    @PutMapping("/update")
    @Operation(summary = "更新用户虚拟货币信息", description = "更新用户虚拟货币信息")
    public Result<String> updateUserVirtualCurrency(@RequestBody UserVirtualCurrency userVirtualCurrency) {
        userVirtualCurrencyService.updateUserVirtualCurrency(userVirtualCurrency);
        return Result.ok("用户虚拟货币信息更新成功");
    }
}