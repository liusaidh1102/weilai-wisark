package com.weilai.streaming.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.streaming.dos.LivePermission;
import com.weilai.streaming.mapper.LivePermissionMapper;
import com.weilai.streaming.service.LivePermissionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 直播权限服务实现类
 */
@Service
public class LivePermissionServiceImpl extends ServiceImpl<LivePermissionMapper, LivePermission> implements LivePermissionService {

    @Resource
    private LivePermissionMapper livePermissionMapper;

    @Override
    public List<LivePermission> getAllLivePermissions() {
        QueryWrapper<LivePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_at");
        return livePermissionMapper.selectList(queryWrapper);
    }

    @Override
    public LivePermission getLivePermissionById(Long id) {
        return livePermissionMapper.selectById(id);
    }

    @Override
    public void addLivePermission(LivePermission livePermission) {
        livePermissionMapper.insert(livePermission);
    }

    @Override
    public void updateLivePermission(LivePermission livePermission) {
        livePermissionMapper.updateById(livePermission);
    }

    @Override
    public void deleteLivePermission(Long id) {
        livePermissionMapper.deleteById(id);
    }
}