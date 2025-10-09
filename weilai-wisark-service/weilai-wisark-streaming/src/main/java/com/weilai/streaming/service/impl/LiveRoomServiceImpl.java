package com.weilai.streaming.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.streaming.dos.LiveRoom;
import com.weilai.streaming.mapper.LiveRoomMapper;
import com.weilai.streaming.service.LiveRoomService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 直播房间服务实现类
 */
@Service
public class LiveRoomServiceImpl extends ServiceImpl<LiveRoomMapper, LiveRoom> implements LiveRoomService {

    @Resource
    private LiveRoomMapper liveRoomMapper;

    @Override
    public List<LiveRoom> getAllLiveRooms() {
        QueryWrapper<LiveRoom> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("deleted_at")
                .orderByDesc("created_at");
        return liveRoomMapper.selectList(queryWrapper);
    }

    @Override
    public LiveRoom getLiveRoomById(Long id) {
        QueryWrapper<LiveRoom> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .isNull("deleted_at");
        return liveRoomMapper.selectOne(queryWrapper);
    }

    @Override
    public void addLiveRoom(LiveRoom liveRoom) {
        liveRoomMapper.insert(liveRoom);
    }

    @Override
    public void updateLiveRoom(LiveRoom liveRoom) {
        liveRoom.setUpdatedAt(new DateTime());
        liveRoomMapper.updateById(liveRoom);
    }

    @Override
    public void deleteLiveRoom(Long id) {
        LiveRoom liveRoom = new LiveRoom();
        liveRoom.setId(id);
        // 软删除，设置删除时间
        liveRoom.setDeletedAt(new DateTime());
        liveRoomMapper.updateById(liveRoom);
    }
}