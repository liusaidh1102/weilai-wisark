package com.weilai.streaming.service;

import com.weilai.model.streaming.dos.LiveAudit;

import java.util.List;

/**
 * 直播审核服务接口
 */
public interface LiveAuditService {

    /**
     * 获取所有直播审核
     *
     * @return 直播审核列表
     */
    List<LiveAudit> getAllLiveAudits();

    /**
     * 根据ID获取直播审核
     *
     * @param id 直播审核ID
     * @return 直播审核详情
     */
    LiveAudit getLiveAuditById(Long id);

    /**
     * 添加直播审核
     *
     * @param liveAudit 直播审核信息
     */
    void addLiveAudit(LiveAudit liveAudit);

    /**
     * 更新直播审核
     *
     * @param liveAudit 直播审核信息
     */
    void updateLiveAudit(LiveAudit liveAudit);
}