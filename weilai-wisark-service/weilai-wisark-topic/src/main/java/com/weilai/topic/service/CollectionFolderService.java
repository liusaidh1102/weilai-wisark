package com.weilai.topic.service;

import com.weilai.model.topic.dos.CollectionFolder;

import java.util.List;

/**
 * 收藏文件夹服务接口
 */
public interface CollectionFolderService {

    /**
     * 获取所有收藏文件夹
     *
     * @return 收藏文件夹列表
     */
    List<CollectionFolder> getAllCollectionFolders();

    /**
     * 根据ID获取收藏文件夹
     *
     * @param id 收藏文件夹ID
     * @return 收藏文件夹详情
     */
    CollectionFolder getCollectionFolderById(Long id);

    /**
     * 添加收藏文件夹
     *
     * @param collectionFolder 收藏文件夹信息
     */
    void addCollectionFolder(CollectionFolder collectionFolder);

    /**
     * 更新收藏文件夹
     *
     * @param collectionFolder 收藏文件夹信息
     */
    void updateCollectionFolder(CollectionFolder collectionFolder);

    /**
     * 删除收藏文件夹
     *
     * @param id 收藏文件夹ID
     */
    void deleteCollectionFolder(Long id);
}