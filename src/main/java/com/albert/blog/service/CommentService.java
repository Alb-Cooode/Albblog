package com.albert.blog.service;

import com.albert.blog.pojo.Comment;

import java.util.List;

/**
 * 评论业务逻辑接口
 */
public interface CommentService {

    /**
     * 新增评论
     * @param comment 评论对象
     * @return 布尔值
     */
    public boolean append(Comment comment);

    /**
     * 根据博客ID
     * @param blogId 博客ID
     * @return 评论列表
     */
    public List<Comment> listComment(int blogId);
}
