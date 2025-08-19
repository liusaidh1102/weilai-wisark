package com.weilai.streaming.service;

import com.weilai.model.streaming.dos.LiveTag;

import java.util.List;

/**
 * 直播标签服务接口
 */
public interface LiveTagService {

    /**
     * 获取所有直播标签
     *
     * @return 直播标签列表
     */
    List<LiveTag> getAllLiveTags();

    /**
     * 根据ID获取直播标签
     *
     * @param id 直播标签ID
     * @return 直播标签详情
     */
    LiveTag getLiveTagById(Long id);

    /**
     * 添加直播标签
     *
     * @param liveTag 直播标签信息
     */
    void addLiveTag(LiveTag liveTag);

    /**
     * 删除直播标签
     *
     * @param id 直播标签ID
     */
    void deleteLiveTag(Long id);
}