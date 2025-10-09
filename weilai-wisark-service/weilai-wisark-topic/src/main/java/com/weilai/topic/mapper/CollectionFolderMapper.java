package com.weilai.topic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.topic.dos.CollectionFolder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收藏文件夹Mapper接口
 */
@Mapper
public interface CollectionFolderMapper extends BaseMapper<CollectionFolder> {
}