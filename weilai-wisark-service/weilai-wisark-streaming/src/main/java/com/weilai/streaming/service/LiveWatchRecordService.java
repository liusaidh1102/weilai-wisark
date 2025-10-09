package com.weilai.streaming.service;

import com.weilai.model.streaming.dos.LiveWatchRecord;

import java.util.List;

/**
 * 直播观看记录服务接口
 */
public interface LiveWatchRecordService {

    /**
     * 获取所有直播观看记录
     *
     * @return 直播观看记录列表
     */
    List<LiveWatchRecord> getAllLiveWatchRecords();

    /**
     * 根据ID获取直播观看记录
     *
     * @param id 直播观看记录ID
     * @return 直播观看记录详情
     */
    LiveWatchRecord getLiveWatchRecordById(Long id);

    /**
     * 添加直播观看记录
     *
     * @param liveWatchRecord 直播观看记录信息
     */
    void addLiveWatchRecord(LiveWatchRecord liveWatchRecord);
}