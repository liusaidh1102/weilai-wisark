package com.weilai.user.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.user.dos.SysPermission;
import com.weilai.model.user.vos.MenuItem;
import com.weilai.user.mapper.PermissionMapper;
import com.weilai.user.service.PermissionService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.weilai.common.constants.CacheConstant.*;
import static com.weilai.common.constants.CacheConstant.USER_PERMISSIONS_EXPIRE;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, SysPermission> implements PermissionService {


    @Resource
    private PermissionMapper permissionMapper;


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<String> getPermissionList(Long userId) {
        // 缓存中查权限信息
        String permissionJson = stringRedisTemplate.opsForValue().get(USER_PERMISSIONS_PREFIX + userId);
        if (!StrUtil.isBlank(permissionJson)) {
            return JSONUtil.toList(permissionJson, String.class);
        }
        List<String> permissionList = permissionMapper.getPermissionList(userId);
        stringRedisTemplate.opsForValue().set(
                USER_PERMISSIONS_PREFIX + userId,
                JSONUtil.toJsonStr(permissionList),
                USER_PERMISSIONS_EXPIRE,
                TimeUnit.SECONDS
        );
        return permissionList;
    }


    @Override
    public List<MenuItem> getMenuList(Long userId) {
        // 缓存中查菜单信息
        String menuJson = stringRedisTemplate.opsForValue().get(USER_MENUS_PREFIX + userId);
        if (!StrUtil.isBlank(menuJson)) {
            return JSONUtil.toList(menuJson, MenuItem.class);
        }
        // 查询菜单
        List<MenuItem> menuItems = permissionMapper.getMenuList(userId);
        // 构建菜单树形结构
        List<MenuItem> menuItemList = buildMenuTree(menuItems);
        // 存入redis
        stringRedisTemplate.opsForValue().set(
                USER_MENUS_PREFIX + userId,
                JSONUtil.toJsonStr(menuItemList),
                USER_MENUS_EXPIRE,
                TimeUnit.SECONDS
        );
        return menuItemList;
    }

    /**
     * 构建菜单树
     *
     * @param menuItems 菜单列表
     * @return 树形结构的菜单列表
     */
    private List<MenuItem> buildMenuTree(List<MenuItem> menuItems) {
        if (menuItems == null || menuItems.isEmpty()) {
            return Collections.emptyList();
        }

        // 1. 整体按照 sort 字段排序
        menuItems.sort(Comparator.comparing(MenuItem::getSort));

        // 2. 创建一个Map用于存储菜单ID和菜单对象的映射关系，方便快速查找
        HashMap<Long, MenuItem> menuMap = new HashMap<>();
        for (MenuItem menu : menuItems) {
            menuMap.put(menu.getId(), menu);
        }

        // 3. 创建一个列表用于存储根节点（最顶层的菜单）
        List<MenuItem> rootMenus = new ArrayList<>();

        // 4. 遍历所有菜单项
        for (MenuItem menu : menuItems) {
            // 5. 获取当前菜单项的父级ID
            Long parentId = menu.getParentId();

            // 6. 如果父级ID为0，表示这是根节点，将其添加到rootMenus列表中
            if (parentId == 0) {
                rootMenus.add(menu);
            } else {
                // 7. 如果不是根节点，则从map中找到其父级菜单
                MenuItem parentMenu = menuMap.get(parentId);
                // 8. 如果找到了父级菜单，则将当前菜单添加到父级菜单的children列表中
                if (parentMenu != null) {
                    if (parentMenu.getChildren() == null) {
                        parentMenu.setChildren(new ArrayList<>());
                    }
                    parentMenu.getChildren().add(menu);
                }
            }
        }

        // 9. 对每个父菜单的子菜单按照 sort 字段排序
        sortChildrenRecursively(rootMenus);

        // 10. 返回根节点列表，此时每个根节点都包含了其所有的子节点，形成树形结构
        return rootMenus;
    }

    /**
     * 递归对每个父菜单的子菜单按照 sort 字段排序
     *
     * @param menuItems 菜单列表
     */
    private void sortChildrenRecursively(List<MenuItem> menuItems) {
        if (menuItems == null || menuItems.isEmpty()) {
            return;
        }
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getChildren() != null && !menuItem.getChildren().isEmpty()) {
                // 对子菜单排序
                menuItem.getChildren().sort(Comparator.comparing(MenuItem::getSort));
                // 递归处理更深层次的子菜单
                sortChildrenRecursively(menuItem.getChildren());
            }
        }
    }

}