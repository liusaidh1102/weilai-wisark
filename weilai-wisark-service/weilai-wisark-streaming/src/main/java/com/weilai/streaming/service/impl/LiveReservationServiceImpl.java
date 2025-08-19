package com.weilai.streaming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.streaming.dos.LiveReservation;
import com.weilai.streaming.mapper.LiveReservationMapper;
import com.weilai.streaming.service.LiveReservationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 直播预约服务实现类
 */
@Service
public class LiveReservationServiceImpl extends ServiceImpl<LiveReservationMapper, LiveReservation> implements LiveReservationService {

    @Resource
    private LiveReservationMapper liveReservationMapper;

    @Override
    public List<LiveReservation> getAllLiveReservations() {
        QueryWrapper<LiveReservation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_at");
        return liveReservationMapper.selectList(queryWrapper);
    }

    @Override
    public LiveReservation getLiveReservationById(Long id) {
        return liveReservationMapper.selectById(id);
    }

    @Override
    public void addLiveReservation(LiveReservation liveReservation) {
        liveReservationMapper.insert(liveReservation);
    }

    @Override
    public void deleteLiveReservation(Long id) {
        liveReservationMapper.deleteById(id);
    }
}