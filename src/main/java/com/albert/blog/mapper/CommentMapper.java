package com.albert.blog.mapper;

import com.albert.blog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论数据访问接口
 */
@Mapper
public interface CommentMapper {

    /**
     * 评论总数
     * @return 评论总数
     */
    public int sumOfComment();

    /**
     * 根据blogId查询该博客下的所有评论，并且无父评论
     * @param blogId
     * @return
     */
    public List<Comment> selectByBlogIdNotParentId(@Param("blogId") int blogId);

    /**
     * 添加评论
     * @param comment 评论对象
     * @return 受影响的行数
     */
    public int insert(Comment comment);

    /**
     * 根据博客ID和一级评论ID查询一级回复
     * @param blogId 博客ID
     * @param topCommentId 一级评论ID
     * @return 一级回复集合
     */
    public List<Comment> selectFirstReply(@Param("blogId") Integer blogId, @Param("topCommentId") Integer topCommentId);

    /**
     * 根据博客ID和一级回复ID查询二级回复
     * @param blogId 博客ID
     * @param firstReplyId 一级回复ID
     * @return 二级回复集合
     */
    public List<Comment> selectSecondReply(@Param("blogId") Integer blogId, @Param("firstReplyId") Integer firstReplyId);

    /**
     * 根据父评论ID查找评论
     * @param parentCommentId 父评论ID
     * @return 评论对象
     */
    public Comment selectByParentId(Integer parentCommentId);

    /**
     * 根据ID删除评论
     * @param id 评论ID
     * @return 受影响的行数
     */
    public int delete(int id);

}
