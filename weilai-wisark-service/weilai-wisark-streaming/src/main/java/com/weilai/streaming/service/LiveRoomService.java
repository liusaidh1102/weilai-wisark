package com.weilai.streaming.service;

import com.weilai.model.streaming.dos.LiveRoom;

import java.util.List;

/**
 * 直播房间服务接口
 */
public interface LiveRoomService {

    /**
     * 获取所有直播房间
     *
     * @return 直播房间列表
     */
    List<LiveRoom> getAllLiveRooms();

    /**
     * 根据ID获取直播房间
     *
     * @param id 直播房间ID
     * @return 直播房间详情
     */
    LiveRoom getLiveRoomById(Long id);

    /**
     * 添加直播房间
     *
     * @param liveRoom 直播房间信息
     */
    void addLiveRoom(LiveRoom liveRoom);

    /**
     * 更新直播房间
     *
     * @param liveRoom 直播房间信息
     */
    void updateLiveRoom(LiveRoom liveRoom);

    /**
     * 删除直播房间
     *
     * @param id 直播房间ID
     */
    void deleteLiveRoom(Long id);
}