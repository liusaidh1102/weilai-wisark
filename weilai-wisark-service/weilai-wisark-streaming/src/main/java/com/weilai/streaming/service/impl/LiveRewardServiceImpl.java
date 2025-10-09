package com.weilai.streaming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.streaming.dos.LiveReward;
import com.weilai.streaming.mapper.LiveRewardMapper;
import com.weilai.streaming.service.LiveRewardService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 直播打赏服务实现类
 */
@Service
public class LiveRewardServiceImpl extends ServiceImpl<LiveRewardMapper, LiveReward> implements LiveRewardService {

    @Resource
    private LiveRewardMapper liveRewardMapper;

    @Override
    public List<LiveReward> getAllLiveRewards() {
        QueryWrapper<LiveReward> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_at");
        return liveRewardMapper.selectList(queryWrapper);
    }

    @Override
    public LiveReward getLiveRewardById(Long id) {
        return liveRewardMapper.selectById(id);
    }

    @Override
    public void addLiveReward(LiveReward liveReward) {
        liveRewardMapper.insert(liveReward);
    }
}