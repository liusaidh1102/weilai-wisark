package com.weilai.topic.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.topic.dos.CollectionTopic;
import com.weilai.topic.mapper.CollectionMapper;
import com.weilai.topic.service.CollectionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章收藏服务实现类
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, CollectionTopic> implements CollectionService {

    @Resource
    private CollectionMapper collectionMapper;

    @Override
    public List<CollectionTopic> getAllCollections() {
        QueryWrapper<CollectionTopic> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("deleted_at")
                .orderByDesc("created_at");
        return collectionMapper.selectList(queryWrapper);
    }

    @Override
    public CollectionTopic getCollectionById(Long id) {
        QueryWrapper<CollectionTopic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .isNull("deleted_at");
        return collectionMapper.selectOne(queryWrapper);
    }

    @Override
    public void addCollection(CollectionTopic collectionTopic) {
        collectionMapper.insert(collectionTopic);
    }

    @Override
    public void updateCollection(CollectionTopic collectionTopic) {
        collectionTopic.setUpdatedAt(new DateTime());
        collectionMapper.updateById(collectionTopic);
    }

    @Override
    public void deleteCollection(Long id) {
        CollectionTopic collectionTopic = new CollectionTopic();
        collectionTopic.setId(id);
        // 软删除，设置删除时间
        collectionTopic.setDeletedAt(new DateTime());
        collectionMapper.updateById(collectionTopic);
    }
}