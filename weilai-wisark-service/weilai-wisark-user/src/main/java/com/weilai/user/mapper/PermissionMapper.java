package com.weilai.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.user.dos.SysPermission;
import com.weilai.model.user.vos.MenuItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<SysPermission> {


    /**
     * 获取用户权限列表
     * @param userId
     * @return
     */
    List<String> getPermissionList(@Param("userId") Long userId);


    List<MenuItem> getMenuList(@Param("userId") Long userId);
}
