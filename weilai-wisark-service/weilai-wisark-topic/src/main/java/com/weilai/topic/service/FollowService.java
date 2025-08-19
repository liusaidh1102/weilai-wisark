package com.weilai.topic.service;

import com.weilai.model.topic.dos.Follow;

import java.util.List;

/**
 * 关注记录服务接口
 */
public interface FollowService {

    /**
     * 获取所有关注记录
     *
     * @return 关注记录列表
     */
    List<Follow> getAllFollows();

    /**
     * 根据ID获取关注记录
     *
     * @param id 关注记录ID
     * @return 关注记录详情
     */
    Follow getFollowById(Long id);

    /**
     * 添加关注记录
     *
     * @param follow 关注记录信息
     */
    void addFollow(Follow follow);

    /**
     * 删除关注记录
     *
     * @param id 关注记录ID
     */
    void deleteFollow(Long id);
}