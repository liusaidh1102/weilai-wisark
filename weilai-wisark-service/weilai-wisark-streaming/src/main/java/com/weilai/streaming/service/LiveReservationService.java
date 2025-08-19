package com.weilai.streaming.service;

import com.weilai.model.streaming.dos.LiveReservation;

import java.util.List;

/**
 * 直播预约服务接口
 */
public interface LiveReservationService {

    /**
     * 获取所有直播预约
     *
     * @return 直播预约列表
     */
    List<LiveReservation> getAllLiveReservations();

    /**
     * 根据ID获取直播预约
     *
     * @param id 直播预约ID
     * @return 直播预约详情
     */
    LiveReservation getLiveReservationById(Long id);

    /**
     * 添加直播预约
     *
     * @param liveReservation 直播预约信息
     */
    void addLiveReservation(LiveReservation liveReservation);

    /**
     * 删除直播预约
     *
     * @param id 直播预约ID
     */
    void deleteLiveReservation(Long id);
}