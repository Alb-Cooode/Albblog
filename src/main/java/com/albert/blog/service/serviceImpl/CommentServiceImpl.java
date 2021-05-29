package com.albert.blog.service.serviceImpl;

import com.albert.blog.mapper.BlogMapper;
import com.albert.blog.mapper.CommentMapper;
import com.albert.blog.pojo.Blog;
import com.albert.blog.pojo.Comment;
import com.albert.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论业务逻辑实现类
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BlogMapper blogMapper;

    private List<Comment> tempReplys = new ArrayList<>();

    @Override
    public boolean append(Comment comment) {
        // 如果parentCommentId != -1即代表其有父亲
        if (comment.getParentCommentId() != -1){
            comment.setParentComment(this.commentMapper.selectByParentId(comment.getParentCommentId()));
        }else {
            comment.setParentComment(null);
        }
        // 增加博客的评论量
        Blog blog = this.blogMapper.getBlogById(comment.getBlog().getId());
        blog.setSumComment(blog.getSumComment() + 1);
        this.blogMapper.update(blog);
        return this.commentMapper.insert(comment) > 0 ? true : false;
    }

    @Override
    public List<Comment> listComment(int blogId) {

        // 查询顶层评论
        List<Comment> comments = this.commentMapper.selectByBlogIdNotParentId(blogId);

        for (Comment comment : comments) {
            Integer topCommentId = comment.getId();
            String parentNickname = comment.getNickname();
            // 一级回复集合
            List<Comment> firstReplies = this.commentMapper.selectFirstReply(blogId,topCommentId);
            // 查询一级回复
            combineFirstReply(blogId,firstReplies,parentNickname);
            // 将遍历结果存放至回复中
            comment.setReplyComment(tempReplys);
            // 遍历结束将tempReplys初始化
            tempReplys = new ArrayList<>();

        }

        return comments;
    }

    public void combineFirstReply(int blogId, List<Comment> firstReplies, String parentNickname){
        // 判断是否有一级子评论
        if (firstReplies.size() > 0){
            // 循环找出子评论的ID
            for (Comment firstReply : firstReplies) {
                Integer firstReplyId = firstReply.getId();
                String firstNickname = firstReply.getNickname();
                // 设置一级回复的父昵称
                firstReply.setParentNickname(parentNickname);
                firstReply.setNickname(firstNickname);
                tempReplys.add(firstReply);
                // 查询二级回复
                combineSecondReply(blogId,firstReplyId,firstNickname);
            }
        }
    }

    public void combineSecondReply(int blogId, int firstReplyId, String firstNickname){
        // 根据一级回复Id找二级回复
        List<Comment> secondReplys = this.commentMapper.selectSecondReply(blogId, firstReplyId);
        // 判断是否有二级回复
        if (secondReplys.size() > 0){
            for (Comment secondReply : secondReplys) {
                Integer secondReplyId = secondReply.getId();
                String secondNickname = secondReply.getNickname();
                // 设置二级回复的父昵称
                secondReply.setParentNickname(firstNickname);
                secondReply.setNickname(secondNickname);
                tempReplys.add(secondReply);
                // 查询二级回复
                combineSecondReply(blogId, secondReplyId, secondNickname);
            }
        }
    }

}
