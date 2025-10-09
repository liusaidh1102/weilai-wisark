package com.weilai.streaming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.streaming.dos.VirtualGift;
import com.weilai.streaming.mapper.VirtualGiftMapper;
import com.weilai.streaming.service.VirtualGiftService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 虚拟礼物服务实现类
 */
@Service
public class VirtualGiftServiceImpl extends ServiceImpl<VirtualGiftMapper, VirtualGift> implements VirtualGiftService {

    @Override
    public List<VirtualGift> getAllVirtualGifts() {
        QueryWrapper<VirtualGift> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_at");
        return this.list(queryWrapper);
    }

    @Override
    public VirtualGift getVirtualGiftById(Long id) {
        return this.getById(id);
    }

    @Override
    public void addVirtualGift(VirtualGift virtualGift) {
        this.save(virtualGift);
    }

    @Override
    public void updateVirtualGift(VirtualGift virtualGift) {
        this.updateById(virtualGift);
    }

    @Override
    public void deleteVirtualGift(Long id) {
        this.removeById(id);
    }
}