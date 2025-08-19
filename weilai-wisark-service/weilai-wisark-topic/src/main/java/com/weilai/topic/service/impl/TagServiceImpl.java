package com.weilai.topic.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.topic.dos.TagTopic;
import com.weilai.topic.mapper.TagMapper;
import com.weilai.topic.service.TagService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签服务实现类
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, TagTopic> implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public List<TagTopic> getAllTags() {
        QueryWrapper<TagTopic> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("deleted_at")
                .orderByDesc("created_at");
        return tagMapper.selectList(queryWrapper);
    }

    @Override
    public TagTopic getTagById(Long id) {
        QueryWrapper<TagTopic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .isNull("deleted_at");
        return tagMapper.selectOne(queryWrapper);
    }

    @Override
    public void addTag(TagTopic tagTopic) {
        tagMapper.insert(tagTopic);
    }

    @Override
    public void updateTag(TagTopic tagTopic) {
        tagTopic.setUpdatedAt(new DateTime());
        tagMapper.updateById(tagTopic);
    }

    @Override
    public void deleteTag(Long id) {
        TagTopic tagTopic = new TagTopic();
        tagTopic.setId(id);
        // 软删除，设置删除时间
        tagTopic.setDeletedAt(new DateTime());
        tagMapper.updateById(tagTopic);
    }
}