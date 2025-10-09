package com.weilai.topic.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.topic.dos.Category;
import com.weilai.model.topic.dtos.CategoryDTO;
import com.weilai.topic.mapper.CategoryMapper;
import com.weilai.topic.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    private  CategoryMapper categoryMapper;
    @Override
    public List<Category> getAllCategories() {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("deleted_at").orderByAsc("sort","created_at");
        return categoryMapper.selectList( queryWrapper);
    }

    @Override
    public Category getCategoryById(Long id) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("deleted_at").eq("id",id);

        return categoryMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(category, categoryDTO);
        category.setCreatedAt(new DateTime());
        category.setUpdatedAt(new DateTime());
        return save(category);
    }

    @Override
    public boolean updateCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(category, categoryDTO);
        category.setUpdatedAt(new DateTime());
        return updateById( category);
    }

    @Override
    public boolean removeById(Long id) {
        Category category = new Category();
        category.setId(id);
        category.setDeletedAt(new DateTime());
        return updateById(category);
    }
}
