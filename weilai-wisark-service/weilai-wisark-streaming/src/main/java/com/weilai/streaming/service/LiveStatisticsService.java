package com.weilai.streaming.service;

import com.weilai.model.streaming.dos.LiveStatistics;

import java.util.List;

/**
 * 直播统计数据服务接口
 */
public interface LiveStatisticsService {

    /**
     * 获取所有直播统计数据
     *
     * @return 直播统计数据列表
     */
    List<LiveStatistics> getAllLiveStatistics();

    /**
     * 根据ID获取直播统计数据
     *
     * @param id 直播统计数据ID
     * @return 直播统计数据详情
     */
    LiveStatistics getLiveStatisticsById(Long id);

    /**
     * 添加直播统计数据
     *
     * @param liveStatistics 直播统计数据信息
     */
    void addLiveStatistics(LiveStatistics liveStatistics);

    /**
     * 更新直播统计数据
     *
     * @param liveStatistics 直播统计数据信息
     */
    void updateLiveStatistics(LiveStatistics liveStatistics);
}