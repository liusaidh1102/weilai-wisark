package com.weilai.topic.controller;

import com.alibaba.nacos.api.model.v2.Result;
import com.weilai.model.topic.dos.Category;
import com.weilai.model.topic.dtos.CategoryDTO;
import com.weilai.topic.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
@Tag(name = "分类模块", description = "分类相关接口")
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @GetMapping("/list")
    @Operation(summary = "获取分类列表", description = "获取所有分类")
    public Result<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return Result.success(categories);
    }
    @GetMapping("/{id}")
    @Operation(summary = "获取分类详情", description = "根据ID获取分类详情")
    public Result<Category> getCategoryById(@PathVariable("id") Long id) {
        Category category = categoryService.getCategoryById(id);
        return Result.success(category);
    }
    @PostMapping("/add")
    @Operation(summary = "添加分类", description = "添加一个新的分类")
    public Result<String> addCategory(@RequestBody CategoryDTO categoryDTO) {
        boolean result = categoryService.addCategory(categoryDTO);
        return result ? Result.success("添加成功") : Result.failure("添加失败");
    }
    @PostMapping("/update")
    @Operation(summary = "更新分类", description = "更新一个分类")
    public Result<String> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        boolean result = categoryService.updateCategory(categoryDTO);
        return result ? Result.success("更新成功") : Result.failure("更新失败");
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除分类", description = "根据ID删除一个分类")
    public Result<String> deleteCategory(@PathVariable("id") Long id) {
        boolean result = categoryService.removeById(id);
        return result ? Result.success("删除成功") : Result.failure("删除失败");
    }
}
