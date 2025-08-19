package com.weilai.topic.controller;

import com.weilai.common.response.Result;
import com.weilai.model.topic.dos.CollectionFolder;
import com.weilai.topic.service.CollectionFolderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收藏文件夹相关接口
 */
@Slf4j
@RestController
@RequestMapping("/collectionFolder")
@Tag(name = "收藏文件夹模块", description = "收藏文件夹相关接口")
public class CollectionFolderController {

    @Resource
    private CollectionFolderService collectionFolderService;

    /**
     * 获取所有收藏文件夹
     *
     * @return 收藏文件夹列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取收藏文件夹列表", description = "获取所有收藏文件夹")
    public Result<List<CollectionFolder>> getAllCollectionFolders() {
        List<CollectionFolder> collectionFolders = collectionFolderService.getAllCollectionFolders();
        return Result.ok(collectionFolders);
    }

    /**
     * 根据ID获取收藏文件夹
     *
     * @param id 收藏文件夹ID
     * @return 收藏文件夹详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取收藏文件夹详情", description = "根据ID获取收藏文件夹详情")
    public Result<CollectionFolder> getCollectionFolderById(@PathVariable Long id) {
        CollectionFolder collectionFolder = collectionFolderService.getCollectionFolderById(id);
        return Result.ok(collectionFolder);
    }

    /**
     * 添加收藏文件夹
     *
     * @param collectionFolder 收藏文件夹信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加收藏文件夹", description = "添加新的收藏文件夹")
    public Result<String> addCollectionFolder(@RequestBody CollectionFolder collectionFolder) {
        collectionFolderService.addCollectionFolder(collectionFolder);
        return Result.ok("收藏文件夹添加成功");
    }

    /**
     * 更新收藏文件夹
     *
     * @param collectionFolder 收藏文件夹信息
     * @return 更新结果
     */
    @PutMapping("/update")
    @Operation(summary = "更新收藏文件夹", description = "更新收藏文件夹信息")
    public Result<String> updateCollectionFolder(@RequestBody CollectionFolder collectionFolder) {
        collectionFolderService.updateCollectionFolder(collectionFolder);
        return Result.ok("收藏文件夹更新成功");
    }

    /**
     * 删除收藏文件夹
     *
     * @param id 收藏文件夹ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除收藏文件夹", description = "根据收藏文件夹ID删除收藏文件夹")
    public Result<String> deleteCollectionFolder(@PathVariable Long id) {
        collectionFolderService.deleteCollectionFolder(id);
        return Result.ok("收藏文件夹删除成功");
    }
}