package com.weilai.topic.service;

import com.weilai.model.topic.dos.TagTopic;
import com.weilai.model.topic.dtos.TagDTO;

import java.util.List;

/**
 * 标签服务接口
 */
public interface TagService {

    /**
     * 获取所有标签
     *
     * @return 标签列表
     */
    List<TagTopic> getAllTags();

    /**
     * 根据ID获取标签
     *
     * @param id 标签ID
     * @return 标签详情
     */
    TagTopic getTagById(Long id);

    /**
     * 添加标签
     *
     * @param tagTopic 标签信息
     */
    void addTag(TagDTO tagTopic);

    /**
     * 更新标签
     *
     * @param tagTopic 标签信息
     */
    void updateTag(TagDTO tagTopic);

    /**
     * 删除标签
     *
     * @param id 标签ID
     */
    void deleteTag(Long id);
}