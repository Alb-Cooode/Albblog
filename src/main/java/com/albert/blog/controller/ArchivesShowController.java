package com.albert.blog.controller;

import com.albert.blog.pojo.Blog;
import com.albert.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 时间轴前台展示控制层
 */
@Controller
public class ArchivesShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public ModelAndView archives(){

        Blog condition = new Blog();
        condition.setPublished(true);

        return new ModelAndView("archives")
                .addObject("blogs",this.blogService.listAll());
    }

}
