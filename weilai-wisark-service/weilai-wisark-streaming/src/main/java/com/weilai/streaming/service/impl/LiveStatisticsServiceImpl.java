package com.weilai.streaming.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.streaming.dos.LiveStatistics;
import com.weilai.streaming.mapper.LiveStatisticsMapper;
import com.weilai.streaming.service.LiveStatisticsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 直播统计数据服务实现类
 */
@Service
public class LiveStatisticsServiceImpl extends ServiceImpl<LiveStatisticsMapper, LiveStatistics> implements LiveStatisticsService {

    @Resource
    private LiveStatisticsMapper liveStatisticsMapper;

    @Override
    public List<LiveStatistics> getAllLiveStatistics() {
        QueryWrapper<LiveStatistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_at");
        return liveStatisticsMapper.selectList(queryWrapper);
    }

    @Override
    public LiveStatistics getLiveStatisticsById(Long id) {
        return liveStatisticsMapper.selectById(id);
    }

    @Override
    public void addLiveStatistics(LiveStatistics liveStatistics) {
        liveStatisticsMapper.insert(liveStatistics);
    }

    @Override
    public void updateLiveStatistics(LiveStatistics liveStatistics) {
        liveStatisticsMapper.updateById(liveStatistics);
    }
}