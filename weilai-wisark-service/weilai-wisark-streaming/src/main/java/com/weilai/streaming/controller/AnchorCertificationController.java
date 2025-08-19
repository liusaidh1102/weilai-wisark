package com.weilai.streaming.controller;

import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.AnchorCertification;
import com.weilai.streaming.service.AnchorCertificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 主播认证相关接口
 */
@Slf4j
@RestController
@RequestMapping("/anchorCertification")
@Tag(name = "主播认证模块", description = "主播认证相关接口")
public class AnchorCertificationController {

    @Resource
    private AnchorCertificationService anchorCertificationService;

    /**
     * 获取所有主播认证信息
     *
     * @return 主播认证信息列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取主播认证信息列表", description = "获取所有主播认证信息")
    public Result<List<AnchorCertification>> getAllAnchorCertifications() {
        List<AnchorCertification> anchorCertifications = anchorCertificationService.getAllAnchorCertifications();
        return Result.ok(anchorCertifications);
    }

    /**
     * 根据ID获取主播认证信息
     *
     * @param id 主播认证ID
     * @return 主播认证信息详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取主播认证信息详情", description = "根据ID获取主播认证信息详情")
    public Result<AnchorCertification> getAnchorCertificationById(@PathVariable Long id) {
        AnchorCertification anchorCertification = anchorCertificationService.getAnchorCertificationById(id);
        return Result.ok(anchorCertification);
    }

    /**
     * 添加主播认证信息
     *
     * @param anchorCertification 主播认证信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加主播认证信息", description = "添加新的主播认证信息")
    public Result<String> addAnchorCertification(@RequestBody AnchorCertification anchorCertification) {
        anchorCertificationService.addAnchorCertification(anchorCertification);
        return Result.ok("主播认证信息添加成功");
    }

    /**
     * 更新主播认证信息
     *
     * @param anchorCertification 主播认证信息
     * @return 更新结果
     */
    @PutMapping("/update")
    @Operation(summary = "更新主播认证信息", description = "更新主播认证信息")
    public Result<String> updateAnchorCertification(@RequestBody AnchorCertification anchorCertification) {
        anchorCertificationService.updateAnchorCertification(anchorCertification);
        return Result.ok("主播认证信息更新成功");
    }

    /**
     * 删除主播认证信息
     *
     * @param id 主播认证ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除主播认证信息", description = "根据主播认证ID删除主播认证信息")
    public Result<String> deleteAnchorCertification(@PathVariable Long id) {
        anchorCertificationService.deleteAnchorCertification(id);
        return Result.ok("主播认证信息删除成功");
    }
}