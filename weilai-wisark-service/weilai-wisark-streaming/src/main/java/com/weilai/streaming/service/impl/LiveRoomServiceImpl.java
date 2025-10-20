package com.weilai.streaming.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.LiveRoom;
import com.weilai.model.streaming.vos.LiveDetailVo;
import com.weilai.model.user.vos.UserVO;
import com.weilai.streaming.mapper.LiveRoomMapper;
import com.weilai.streaming.service.LiveRoomService;
import com.wisark.api.feign.user.UserClient;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 直播房间服务实现类
 */
@Service
public class LiveRoomServiceImpl extends ServiceImpl<LiveRoomMapper, LiveRoom> implements LiveRoomService {

    @Resource
    private LiveRoomMapper liveRoomMapper;
    @Resource
    private UserClient userClient;

    @Override
    public List<LiveRoom> getAllLiveRooms() {
        QueryWrapper<LiveRoom> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("deleted_at")
                .orderByDesc("created_at");
        return liveRoomMapper.selectList(queryWrapper);
    }

    @Override
    public LiveDetailVo selectLiveRoomById(String id) {
        LiveDetailVo liveDetailVo = new LiveDetailVo();
        QueryWrapper<LiveRoom> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .isNull("deleted_at");
        LiveRoom liveRoom = liveRoomMapper.selectOne(queryWrapper);
        BeanUtils.copyProperties(liveRoom, liveDetailVo);
        liveDetailVo.setUserInfo(userClient.getUserInfo().getData());
        return liveDetailVo;
    }

    @Override
    public Result<String> addLiveRoom(LiveRoom liveRoom) {
        Result<UserVO> userInfo = userClient.getUserInfo();
        Long userId = userInfo.getData().getId();
        liveRoom.setUserId(userId.toString());
        liveRoom.setCreatedAt(LocalDateTime.now());
        liveRoom.setUpdatedAt(LocalDateTime.now());
        LambdaQueryWrapper<LiveRoom> wapper = new LambdaQueryWrapper<>();
        wapper.eq(LiveRoom::getUserId, userId).eq(LiveRoom::getDeletedAt, null);
        LiveRoom liveRoom1 = liveRoomMapper.selectOne(wapper);
        if(liveRoom1 != null){
            return Result.fail("您已创建过直播间，请勿重复创建");
        }
        return liveRoomMapper.insert(liveRoom) > 0 ? Result.ok("创建成功"): Result.fail("创建失败");

    }

    @Override
    public void updateLiveRoom(LiveRoom liveRoom) {
        liveRoom.setUpdatedAt(LocalDateTime.now());
        liveRoomMapper.updateById(liveRoom);
    }

    @Override
    public void deleteLiveRoom(String id) {
        LiveRoom liveRoom = new LiveRoom();
        liveRoom.setId(id);
        // 软删除，设置删除时间
        liveRoom.setDeletedAt(LocalDateTime.now());
        liveRoomMapper.updateById(liveRoom);
    }
}