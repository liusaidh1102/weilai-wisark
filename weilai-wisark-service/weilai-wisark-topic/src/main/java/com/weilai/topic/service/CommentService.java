package com.weilai.topic.service;

import com.weilai.model.topic.dos.Comment;

import java.util.List;

/**
 * 评论服务接口
 */
public interface CommentService {

    /**
     * 根据文章ID获取评论列表
     *
     * @param articleId 文章ID
     * @return 评论列表
     */
    List<Comment> getCommentsByArticleId(Long articleId);

    /**
     * 添加评论
     *
     * @param comment 评论信息
     */
    void addComment(Comment comment);

    /**
     * 删除评论
     *
     * @param id 评论ID
     */
    void deleteComment(Long id);
}