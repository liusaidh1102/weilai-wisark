package com.weilai.topic.service;

import com.weilai.model.topic.dos.Like;

import java.util.List;

/**
 * 点赞记录服务接口
 */
public interface LikeService {

    /**
     * 获取所有点赞记录
     *
     * @return 点赞记录列表
     */
    List<Like> getAllLikes();

    /**
     * 根据ID获取点赞记录
     *
     * @param id 点赞记录ID
     * @return 点赞记录详情
     */
    Like getLikeById(Long id);

    /**
     * 添加点赞记录
     *
     * @param like 点赞记录信息
     */
    void addLike(Like like);

    /**
     * 删除点赞记录
     *
     * @param id 点赞记录ID
     */
    void deleteLike(Long id);
}