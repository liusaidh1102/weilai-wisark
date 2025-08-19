package com.weilai.topic.service;

import com.weilai.model.topic.dos.UserBehavior;

import java.util.List;

/**
 * 用户行为追踪服务接口
 */
public interface UserBehaviorService {

    /**
     * 获取所有用户行为追踪记录
     *
     * @return 用户行为追踪记录列表
     */
    List<UserBehavior> getAllUserBehaviors();

    /**
     * 根据ID获取用户行为追踪记录
     *
     * @param id 用户行为追踪记录ID
     * @return 用户行为追踪记录详情
     */
    UserBehavior getUserBehaviorById(Long id);

    /**
     * 添加用户行为追踪记录
     *
     * @param userBehavior 用户行为追踪记录信息
     */
    void addUserBehavior(UserBehavior userBehavior);
}