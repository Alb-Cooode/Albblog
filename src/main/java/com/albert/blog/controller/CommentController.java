package com.albert.blog.controller;

import com.albert.blog.pojo.Comment;
import com.albert.blog.pojo.User;
import com.albert.blog.service.BlogService;
import com.albert.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * 评论展示控制层
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    // 从配置文件中取头像
    @Value("${comment.img}")
    private String commentAva;

    // 跳转到博客详情页面中评论区
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable("blogId") Integer blogId, Model model){
        model.addAttribute("comments",this.commentService.listComment(blogId));
        return "detail::commentList";
    }

    // 新增评论
    @PostMapping("/comments")
    public String append(Comment comment, HttpSession session, Model model){
        // 判断是否为站长
        int blogId = comment.getBlogId();
        comment.setBlog(this.blogService.detail(blogId));
        User user = (User) session.getAttribute("user");
        if (user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else {
            comment.setAvatar(this.commentAva);
        }
        this.commentService.append(comment);
        return "redirect:/comments/" + blogId;
    }

}
