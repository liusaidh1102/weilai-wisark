package com.weilai.topic.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.topic.dos.Comment;
import com.weilai.topic.mapper.CommentMapper;
import com.weilai.topic.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论服务实现类
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentsByArticleId(Long articleId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId)
                .isNull("deleted_at")
                .orderByDesc("created_at");
        return commentMapper.selectList(queryWrapper);
    }

    @Override
    public void addComment(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = new Comment();
        comment.setId(id);
        // 软删除，设置删除时间
        comment.setDeletedAt(new DateTime());
        commentMapper.updateById(comment);
    }
}