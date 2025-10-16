package com.weilai.streaming.service;

/**
 * 直播媒体流服务接口
 */
public interface LiveStreamerService {
    /**
     * 启动直播
     * @return
     */
    void startLive(String roomId);

    /**
     * 结束直播
     * @param roomId
     */
    void stopLive(String roomId, String replayUrl);
}
