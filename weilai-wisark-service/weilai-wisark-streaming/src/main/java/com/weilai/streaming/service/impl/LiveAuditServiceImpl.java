package com.weilai.streaming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.streaming.dos.LiveAudit;
import com.weilai.streaming.mapper.LiveAuditMapper;
import com.weilai.streaming.service.LiveAuditService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 直播审核服务实现类
 */
@Service
public class LiveAuditServiceImpl extends ServiceImpl<LiveAuditMapper, LiveAudit> implements LiveAuditService {

    @Resource
    private LiveAuditMapper liveAuditMapper;

    @Override
    public List<LiveAudit> getAllLiveAudits() {
        QueryWrapper<LiveAudit> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_at");
        return liveAuditMapper.selectList(queryWrapper);
    }

    @Override
    public LiveAudit getLiveAuditById(Long id) {
        return liveAuditMapper.selectById(id);
    }

    @Override
    public void addLiveAudit(LiveAudit liveAudit) {
        liveAuditMapper.insert(liveAudit);
    }

    @Override
    public void updateLiveAudit(LiveAudit liveAudit) {
        liveAuditMapper.updateById(liveAudit);
    }
}