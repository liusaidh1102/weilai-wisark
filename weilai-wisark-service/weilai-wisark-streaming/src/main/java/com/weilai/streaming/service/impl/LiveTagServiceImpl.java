package com.weilai.streaming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.streaming.dos.LiveTag;
import com.weilai.streaming.mapper.LiveTagMapper;
import com.weilai.streaming.service.LiveTagService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 直播标签服务实现类
 */
@Service
public class LiveTagServiceImpl extends ServiceImpl<LiveTagMapper, LiveTag> implements LiveTagService {

    @Resource
    private LiveTagMapper liveTagMapper;

    @Override
    public List<LiveTag> getAllLiveTags() {
        QueryWrapper<LiveTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_at");
        return liveTagMapper.selectList(queryWrapper);
    }

    @Override
    public LiveTag getLiveTagById(Long id) {
        return liveTagMapper.selectById(id);
    }

    @Override
    public void addLiveTag(LiveTag liveTag) {
        liveTagMapper.insert(liveTag);
    }

    @Override
    public void deleteLiveTag(Long id) {
        liveTagMapper.deleteById(id);
    }
}