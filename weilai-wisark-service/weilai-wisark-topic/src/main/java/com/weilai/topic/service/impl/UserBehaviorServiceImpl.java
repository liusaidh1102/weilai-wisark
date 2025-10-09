package com.weilai.topic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.topic.dos.UserBehavior;
import com.weilai.topic.mapper.UserBehaviorMapper;
import com.weilai.topic.service.UserBehaviorService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户行为追踪服务实现类
 */
@Service
public class UserBehaviorServiceImpl extends ServiceImpl<UserBehaviorMapper, UserBehavior> implements UserBehaviorService {

    @Resource
    private UserBehaviorMapper userBehaviorMapper;

    @Override
    public List<UserBehavior> getAllUserBehaviors() {
        QueryWrapper<UserBehavior> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_at");
        return userBehaviorMapper.selectList(queryWrapper);
    }

    @Override
    public UserBehavior getUserBehaviorById(Long id) {
        return userBehaviorMapper.selectById(id);
    }

    @Override
    public void addUserBehavior(UserBehavior userBehavior) {
        userBehaviorMapper.insert(userBehavior);
    }
}