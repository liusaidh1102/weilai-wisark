package com.weilai.topic.service;

import com.weilai.model.topic.dos.Category;
import com.weilai.model.topic.dtos.CategoryDTO;

import java.util.List;

/**
 * 分类接口
 */
public interface CategoryService {
    /**
     * 获取所有分类
     * @return
     */
    List<Category> getAllCategories();

    /**
     * 根据ID获取分类
     * @param id
     * @return
     */
    Category getCategoryById(Long id);

    /**
     * 添加一个 分类
     * @param category
     * @return
     */
    boolean addCategory(CategoryDTO category);

    /**
     * 跟新一个 分类
     * @param categoryDTO
     * @return
     */
    boolean updateCategory(CategoryDTO categoryDTO);

    /**
     * 删除一个 分类
     * @param id
     * @return
     */
    boolean removeById(Long id);
}
