package com.weilai.streaming.service;

import com.weilai.model.streaming.dos.SystemNotice;

import java.util.List;

/**
 * 系统通知服务接口
 */
public interface SystemNoticeService {

    /**
     * 获取所有系统通知
     *
     * @return 系统通知列表
     */
    List<SystemNotice> getAllSystemNotices();

    /**
     * 根据ID获取系统通知
     *
     * @param id 系统通知ID
     * @return 系统通知详情
     */
    SystemNotice getSystemNoticeById(Long id);

    /**
     * 添加系统通知
     *
     * @param systemNotice 系统通知信息
     */
    void addSystemNotice(SystemNotice systemNotice);

    /**
     * 更新系统通知
     *
     * @param systemNotice 系统通知信息
     */
    void updateSystemNotice(SystemNotice systemNotice);

    /**
     * 删除系统通知
     *
     * @param id 系统通知ID
     */
    void deleteSystemNotice(Long id);
}