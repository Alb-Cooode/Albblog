package com.albert.blog.controller;

import com.albert.blog.service.BlogService;
import com.albert.blog.service.UserService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 首页控制器
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    // 通过get方式请求路径
    @RequestMapping
    public ModelAndView index(@RequestParam(value = "page", defaultValue = "1") Integer page, HttpSession session){

        session.removeAttribute("blogTotal");
        session.removeAttribute("blogViewTotal");
        session.removeAttribute("blogCommentTotal");
        session.removeAttribute("blogMessageTotal");

        // 因为这些信息都是在共享页面中存在，所以放至session中
        session.setAttribute("blogTotal",this.blogService.sumOfBlog());
        session.setAttribute("blogViewTotal",this.blogService.sumOfViews());
        session.setAttribute("blogCommentTotal",this.blogService.sumOfComment());
        session.setAttribute("blogMessageTotal",this.blogService.sumOfMessages());

        return new ModelAndView("index")
                .addObject("recommendBlogs",this.blogService.listByRecommend())
                .addObject("pager",this.blogService.list(page));
    }

    @GetMapping("/detail")
    public ModelAndView detai(Integer id) throws NotFoundException{
        return new ModelAndView("detail")
                .addObject("blog",this.blogService.detailWithMarkdown(id));
    }

    @PostMapping("/search")
    public ModelAndView search(String text, @RequestParam(value = "page", defaultValue = "1") Integer page){
        return new ModelAndView("search")
                .addObject("pager",this.blogService.listByText(text,page))
                .addObject("text",text)
                .addObject("user",this.userService.selectById(1));
    }

}
