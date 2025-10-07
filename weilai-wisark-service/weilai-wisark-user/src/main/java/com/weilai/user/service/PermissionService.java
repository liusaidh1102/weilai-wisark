package com.weilai.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.model.user.dos.SysPermission;
import com.weilai.model.user.vos.MenuItem;

import java.util.List;

public interface PermissionService extends IService<SysPermission> {

    /**
     * 获取用户权限列表
     * @param userId
     * @return
     */
    List<String> getPermissionList(Long userId);


    /**
     * 获取用户菜单列表
     * @param userId
     * @return
     */
    List<MenuItem> getMenuList(Long userId);



}