package com.weilai.streaming.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.streaming.dos.LiveMessage;
import com.weilai.streaming.mapper.LiveMessageMapper;
import com.weilai.streaming.service.LiveMessageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 直播消息服务实现类
 */
@Service
public class LiveMessageServiceImpl extends ServiceImpl<LiveMessageMapper, LiveMessage> implements LiveMessageService {

    @Resource
    private LiveMessageMapper liveMessageMapper;

    @Override
    public List<LiveMessage> getAllLiveMessages() {
        QueryWrapper<LiveMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("deleted_at")
                .orderByDesc("created_at");
        return liveMessageMapper.selectList(queryWrapper);
    }

    @Override
    public LiveMessage getLiveMessageById(Long id) {
        QueryWrapper<LiveMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .isNull("deleted_at");
        return liveMessageMapper.selectOne(queryWrapper);
    }

    @Override
    public void addLiveMessage(LiveMessage liveMessage) {
        liveMessageMapper.insert(liveMessage);
    }

    @Override
    public void deleteLiveMessage(Long id) {
        LiveMessage liveMessage = new LiveMessage();
        liveMessage.setId(id);
        // 软删除，设置删除时间
        liveMessage.setDeletedAt(new DateTime());
        liveMessageMapper.updateById(liveMessage);
    }
}