package com.weilai.streaming.service;

import com.weilai.model.streaming.dos.LiveMessage;

import java.util.List;

/**
 * 直播消息服务接口
 */
public interface LiveMessageService {

    /**
     * 获取所有直播消息
     *
     * @return 直播消息列表
     */
    List<LiveMessage> getAllLiveMessages();

    /**
     * 根据ID获取直播消息
     *
     * @param id 直播消息ID
     * @return 直播消息详情
     */
    LiveMessage getLiveMessageById(Long id);

    /**
     * 添加直播消息
     *
     * @param liveMessage 直播消息信息
     */
    void addLiveMessage(LiveMessage liveMessage);

    /**
     * 删除直播消息
     *
     * @param id 直播消息ID
     */
    void deleteLiveMessage(Long id);
}