package com.weilai.streaming.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.streaming.dos.SystemNotice;
import com.weilai.streaming.mapper.SystemNoticeMapper;
import com.weilai.streaming.service.SystemNoticeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统通知服务实现类
 */
@Service
public class SystemNoticeServiceImpl extends ServiceImpl<SystemNoticeMapper, SystemNotice> implements SystemNoticeService {

    @Resource
    private SystemNoticeMapper systemNoticeMapper;

    @Override
    public List<SystemNotice> getAllSystemNotices() {
        QueryWrapper<SystemNotice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_at");
        return systemNoticeMapper.selectList(queryWrapper);
    }

    @Override
    public SystemNotice getSystemNoticeById(Long id) {
        return systemNoticeMapper.selectById(id);
    }

    @Override
    public void addSystemNotice(SystemNotice systemNotice) {
        systemNoticeMapper.insert(systemNotice);
    }

    @Override
    public void updateSystemNotice(SystemNotice systemNotice) {
        systemNoticeMapper.updateById(systemNotice);
    }

    @Override
    public void deleteSystemNotice(Long id) {
        systemNoticeMapper.deleteById(id);
    }
}