package com.weilai.streaming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.streaming.dos.LiveWatchRecord;
import com.weilai.streaming.mapper.LiveWatchRecordMapper;
import com.weilai.streaming.service.LiveWatchRecordService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 直播观看记录服务实现类
 */
@Service
public class LiveWatchRecordServiceImpl extends ServiceImpl<LiveWatchRecordMapper, LiveWatchRecord> implements LiveWatchRecordService {

    @Resource
    private LiveWatchRecordMapper liveWatchRecordMapper;

    @Override
    public List<LiveWatchRecord> getAllLiveWatchRecords() {
        QueryWrapper<LiveWatchRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_at");
        return liveWatchRecordMapper.selectList(queryWrapper);
    }

    @Override
    public LiveWatchRecord getLiveWatchRecordById(Long id) {
        return liveWatchRecordMapper.selectById(id);
    }

    @Override
    public void addLiveWatchRecord(LiveWatchRecord liveWatchRecord) {
        liveWatchRecordMapper.insert(liveWatchRecord);
    }
}