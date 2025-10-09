package com.weilai.streaming.service;

import com.weilai.model.streaming.dos.VirtualGift;

import java.util.List;

/**
 * 虚拟礼物服务接口
 */
public interface VirtualGiftService {

    /**
     * 获取所有虚拟礼物
     *
     * @return 虚拟礼物列表
     */
    List<VirtualGift> getAllVirtualGifts();

    /**
     * 根据ID获取虚拟礼物
     *
     * @param id 虚拟礼物ID
     * @return 虚拟礼物详情
     */
    VirtualGift getVirtualGiftById(Long id);

    /**
     * 添加虚拟礼物
     *
     * @param virtualGift 虚拟礼物信息
     */
    void addVirtualGift(VirtualGift virtualGift);

    /**
     * 更新虚拟礼物
     *
     * @param virtualGift 虚拟礼物信息
     */
    void updateVirtualGift(VirtualGift virtualGift);

    /**
     * 删除虚拟礼物
     *
     * @param id 虚拟礼物ID
     */
    void deleteVirtualGift(Long id);
}