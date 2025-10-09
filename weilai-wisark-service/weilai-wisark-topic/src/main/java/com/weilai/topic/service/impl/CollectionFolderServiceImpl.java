package com.weilai.topic.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.topic.dos.CollectionFolder;
import com.weilai.topic.mapper.CollectionFolderMapper;
import com.weilai.topic.service.CollectionFolderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收藏文件夹服务实现类
 */
@Service
public class CollectionFolderServiceImpl extends ServiceImpl<CollectionFolderMapper, CollectionFolder> implements CollectionFolderService {

    @Resource
    private CollectionFolderMapper collectionFolderMapper;

    @Override
    public List<CollectionFolder> getAllCollectionFolders() {
        QueryWrapper<CollectionFolder> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("deleted_at")
                .orderByDesc("created_at");
        return collectionFolderMapper.selectList(queryWrapper);
    }

    @Override
    public CollectionFolder getCollectionFolderById(Long id) {
        QueryWrapper<CollectionFolder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .isNull("deleted_at");
        return collectionFolderMapper.selectOne(queryWrapper);
    }

    @Override
    public void addCollectionFolder(CollectionFolder collectionFolder) {
        collectionFolderMapper.insert(collectionFolder);
    }

    @Override
    public void updateCollectionFolder(CollectionFolder collectionFolder) {
        collectionFolder.setUpdatedAt(new DateTime());
        collectionFolderMapper.updateById(collectionFolder);
    }

    @Override
    public void deleteCollectionFolder(Long id) {
        CollectionFolder collectionFolder = new CollectionFolder();
        collectionFolder.setId(id);
        // 软删除，设置删除时间
        collectionFolder.setDeletedAt(new DateTime());
        collectionFolderMapper.updateById(collectionFolder);
    }
}