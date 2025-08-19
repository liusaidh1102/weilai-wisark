package com.weilai.streaming.service;

import com.weilai.model.streaming.dos.LiveAllowUser;

import java.util.List;

/**
 * 直播允许用户服务接口
 */
public interface LiveAllowUserService {

    /**
     * 获取所有直播允许用户
     *
     * @return 直播允许用户列表
     */
    List<LiveAllowUser> getAllLiveAllowUsers();

    /**
     * 根据ID获取直播允许用户
     *
     * @param id 直播允许用户ID
     * @return 直播允许用户详情
     */
    LiveAllowUser getLiveAllowUserById(Long id);

    /**
     * 添加直播允许用户
     *
     * @param liveAllowUser 直播允许用户信息
     */
    void addLiveAllowUser(LiveAllowUser liveAllowUser);

    /**
     * 删除直播允许用户
     *
     * @param id 直播允许用户ID
     */
    void deleteLiveAllowUser(Long id);
}