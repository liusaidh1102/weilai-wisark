package com.weilai.topic.controller;

import com.weilai.common.response.Result;
import com.weilai.model.topic.dos.CollectionTopic;
import com.weilai.topic.service.CollectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章收藏相关接口
 */
@Slf4j
@RestController
@RequestMapping("/collection")
@Tag(name = "文章收藏模块", description = "文章收藏相关接口")
public class CollectionController {

    @Resource
    private CollectionService collectionService;

    /**
     * 获取所有文章收藏
     *
     * @return 文章收藏列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取文章收藏列表", description = "获取所有文章收藏")
    public Result<List<CollectionTopic>> getAllCollections() {
        List<CollectionTopic> collectionTopics = collectionService.getAllCollections();
        return Result.ok(collectionTopics);
    }

    /**
     * 根据ID获取文章收藏
     *
     * @param id 文章收藏ID
     * @return 文章收藏详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取文章收藏详情", description = "根据ID获取文章收藏详情")
    public Result<CollectionTopic> getCollectionById(@PathVariable Long id) {
        CollectionTopic collectionTopic = collectionService.getCollectionById(id);
        return Result.ok(collectionTopic);
    }

    /**
     * 添加文章收藏
     *
     * @param collectionTopic 文章收藏信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加文章收藏", description = "添加新的文章收藏")
    public Result<String> addCollection(@RequestBody CollectionTopic collectionTopic) {
        collectionService.addCollection(collectionTopic);
        return Result.ok("文章收藏添加成功");
    }

    /**
     * 更新文章收藏
     *
     * @param collectionTopic 文章收藏信息
     * @return 更新结果
     */
    @PutMapping("/update")
    @Operation(summary = "更新文章收藏", description = "更新文章收藏信息")
    public Result<String> updateCollection(@RequestBody CollectionTopic collectionTopic) {
        collectionService.updateCollection(collectionTopic);
        return Result.ok("文章收藏更新成功");
    }

    /**
     * 删除文章收藏
     *
     * @param id 文章收藏ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除文章收藏", description = "根据文章收藏ID删除文章收藏")
    public Result<String> deleteCollection(@PathVariable Long id) {
        collectionService.deleteCollection(id);
        return Result.ok("文章收藏删除成功");
    }
}