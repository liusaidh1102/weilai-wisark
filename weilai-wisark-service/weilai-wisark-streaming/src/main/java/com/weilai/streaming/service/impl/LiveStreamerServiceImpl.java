package com.weilai.streaming.service.impl;

import com.weilai.model.streaming.dos.LiveRoom;
import com.weilai.streaming.service.LiveStreamerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class LiveStreamerServiceImpl implements LiveStreamerService {
    @Resource
    private LiveRoomServiceImpl liveRoomService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startLive(String liveRoomId) {
        LiveRoom liveRoom = liveRoomService.getById(liveRoomId);
        liveRoom.setStatus(1);
        liveRoom.setActualStartTime(LocalDateTime.now());
        liveRoomService.updateLiveRoom(liveRoom);
    }

    @Override
    public void stopLive(String liveRoomId, String replayUrl) {
        LiveRoom liveRoom = liveRoomService.getById(liveRoomId);
        liveRoom.setStatus(2);
        liveRoom.setEndTime(LocalDateTime.now());
        liveRoom.setReplayUrl(replayUrl);
        liveRoomService.updateLiveRoom(liveRoom);
    }
}
