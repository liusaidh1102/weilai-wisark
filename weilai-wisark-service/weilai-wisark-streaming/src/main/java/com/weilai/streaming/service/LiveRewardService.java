package com.weilai.streaming.service;

import com.weilai.model.streaming.dos.LiveReward;

import java.util.List;

/**
 * 直播打赏服务接口
 */
public interface LiveRewardService {

    /**
     * 获取所有直播打赏
     *
     * @return 直播打赏列表
     */
    List<LiveReward> getAllLiveRewards();

    /**
     * 根据ID获取直播打赏
     *
     * @param id 直播打赏ID
     * @return 直播打赏详情
     */
    LiveReward getLiveRewardById(Long id);

    /**
     * 添加直播打赏
     *
     * @param liveReward 直播打赏信息
     */
    void addLiveReward(LiveReward liveReward);
}