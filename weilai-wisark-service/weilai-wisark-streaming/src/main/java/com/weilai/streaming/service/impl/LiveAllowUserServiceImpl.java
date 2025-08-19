package com.weilai.streaming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.streaming.dos.LiveAllowUser;
import com.weilai.streaming.mapper.LiveAllowUserMapper;
import com.weilai.streaming.service.LiveAllowUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 直播允许用户服务实现类
 */
@Service
public class LiveAllowUserServiceImpl extends ServiceImpl<LiveAllowUserMapper, LiveAllowUser> implements LiveAllowUserService {

    @Resource
    private LiveAllowUserMapper liveAllowUserMapper;

    @Override
    public List<LiveAllowUser> getAllLiveAllowUsers() {
        QueryWrapper<LiveAllowUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_at");
        return liveAllowUserMapper.selectList(queryWrapper);
    }

    @Override
    public LiveAllowUser getLiveAllowUserById(Long id) {
        return liveAllowUserMapper.selectById(id);
    }

    @Override
    public void addLiveAllowUser(LiveAllowUser liveAllowUser) {
        liveAllowUserMapper.insert(liveAllowUser);
    }

    @Override
    public void deleteLiveAllowUser(Long id) {
        liveAllowUserMapper.deleteById(id);
    }
}