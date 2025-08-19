package com.weilai.topic.service;

import com.weilai.model.topic.dos.CollectionTopic;

import java.util.List;

/**
 * 文章收藏服务接口
 */
public interface CollectionService {

    /**
     * 获取所有文章收藏
     *
     * @return 文章收藏列表
     */
    List<CollectionTopic> getAllCollections();

    /**
     * 根据ID获取文章收藏
     *
     * @param id 文章收藏ID
     * @return 文章收藏详情
     */
    CollectionTopic getCollectionById(Long id);

    /**
     * 添加文章收藏
     *
     * @param collectionTopic 文章收藏信息
     */
    void addCollection(CollectionTopic collectionTopic);

    /**
     * 更新文章收藏
     *
     * @param collectionTopic 文章收藏信息
     */
    void updateCollection(CollectionTopic collectionTopic);

    /**
     * 删除文章收藏
     *
     * @param id 文章收藏ID
     */
    void deleteCollection(Long id);
}