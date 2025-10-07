package com.weilai.user.controller;
import com.weilai.model.user.vos.MenuItem;
import com.weilai.user.service.PermissionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * 角色权限
 */
@RestController
@RequestMapping("/user")
public class PermissionController {


    @Resource
    private PermissionService permissionService;


    /**
     * 获取当前用户权限
     * @return user:list  user:delete  user:add ....
     */
    @GetMapping("/button/{userId}")
    public List<String> getPermissionList(@PathVariable Long userId) {
        return permissionService.getPermissionList(userId);
    }

    @GetMapping("/menu/{userId}")
    public List<MenuItem> getMenuList(@PathVariable Long userId) {
        return permissionService.getMenuList(userId);
    }

}