package com.weilai.topic.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.topic.dos.Follow;
import com.weilai.topic.mapper.FollowMapper;
import com.weilai.topic.service.FollowService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 关注记录服务实现类
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

    @Resource
    private FollowMapper followMapper;

    @Override
    public List<Follow> getAllFollows() {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("deleted_at")
                .orderByDesc("created_at");
        return followMapper.selectList(queryWrapper);
    }

    @Override
    public Follow getFollowById(Long id) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .isNull("deleted_at");
        return followMapper.selectOne(queryWrapper);
    }

    @Override
    public void addFollow(Follow follow) {
        followMapper.insert(follow);
    }

    @Override
    public void deleteFollow(Long id) {
        Follow follow = new Follow();
        follow.setId(id);
        // 软删除，设置删除时间
        follow.setDeletedAt(new DateTime());
        followMapper.updateById(follow);
    }
}