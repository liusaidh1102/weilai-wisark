package com.weilai.streaming.service;

import com.weilai.model.streaming.dos.LivePermission;

import java.util.List;

/**
 * 直播权限服务接口
 */
public interface LivePermissionService {

    /**
     * 获取所有直播权限
     *
     * @return 直播权限列表
     */
    List<LivePermission> getAllLivePermissions();

    /**
     * 根据ID获取直播权限
     *
     * @param id 直播权限ID
     * @return 直播权限详情
     */
    LivePermission getLivePermissionById(Long id);

    /**
     * 添加直播权限
     *
     * @param livePermission 直播权限信息
     */
    void addLivePermission(LivePermission livePermission);

    /**
     * 更新直播权限
     *
     * @param livePermission 直播权限信息
     */
    void updateLivePermission(LivePermission livePermission);

    /**
     * 删除直播权限
     *
     * @param id 直播权限ID
     */
    void deleteLivePermission(Long id);
}