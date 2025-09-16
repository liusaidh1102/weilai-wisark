package com.weilai.topic.controller;

import com.weilai.common.response.Result;
import com.weilai.model.topic.dos.TagTopic;
import com.weilai.model.topic.dtos.TagDTO;
import com.weilai.topic.service.TagService;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签相关接口
 */
@Slf4j
@RestController
@RequestMapping("/tag")
@Tag(name = "标签模块", description = "标签相关接口")
public class TagController {

    @Resource
    private TagService tagService;

    /**
     * 获取所有标签
     *
     * @return 标签列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取标签列表", description = "获取所有标签")
    public Result<List<TagTopic>> getAllTags() {
        List<TagTopic> tagTopics = tagService.getAllTags();
        return Result.ok(tagTopics);
    }

    /**
     * 根据ID获取标签
     *
     * @param id 标签ID
     * @return 标签详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取标签详情", description = "根据ID获取标签详情")
    public Result<TagTopic> getTagById(@PathVariable Long id) {
        TagTopic tagTopic = tagService.getTagById(id);
        return Result.ok(tagTopic);
    }

    /**
     * 添加标签
     *
     * @param tagTopic 标签信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加标签", description = "添加新的标签")
    public Result<String> addTag(@RequestBody TagDTO tagTopic) {
        tagService.addTag(tagTopic);
        return Result.ok("标签添加成功");
    }

    /**
     * 更新标签
     *
     * @param tagTopic 标签信息
     * @return 更新结果
     */
    @PutMapping("/update")
    @Operation(summary = "更新标签", description = "更新标签信息")
    public Result<String> updateTag(@RequestBody TagDTO tagTopic) {
        tagService.updateTag(tagTopic);
        return Result.ok("标签更新成功");
    }

    /**
     * 删除标签
     *
     * @param id 标签ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除标签", description = "根据标签ID删除标签")
    public Result<String> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return Result.ok("标签删除成功");
    }
}