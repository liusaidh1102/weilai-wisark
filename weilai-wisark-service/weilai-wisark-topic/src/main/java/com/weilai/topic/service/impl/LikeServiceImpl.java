package com.weilai.topic.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.topic.dos.Like;
import com.weilai.topic.mapper.LikeMapper;
import com.weilai.topic.service.LikeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 点赞记录服务实现类
 */
@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements LikeService {

    @Resource
    private LikeMapper likeMapper;

    @Override
    public List<Like> getAllLikes() {
        QueryWrapper<Like> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("deleted_at")
                .orderByDesc("created_at");
        return likeMapper.selectList(queryWrapper);
    }

    @Override
    public Like getLikeById(Long id) {
        QueryWrapper<Like> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .isNull("deleted_at");
        return likeMapper.selectOne(queryWrapper);
    }

    @Override
    public void addLike(Like like) {
        likeMapper.insert(like);
    }

    @Override
    public void deleteLike(Long id) {
        Like like = new Like();
        like.setId(id);
        // 软删除，设置删除时间
        like.setDeletedAt(new DateTime());
        likeMapper.updateById(like);
    }
}