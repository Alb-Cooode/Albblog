package com.albert.blog.controller.admin;

import com.albert.blog.pojo.Blog;
import com.albert.blog.pojo.BlogQuery;
import com.albert.blog.pojo.User;
import com.albert.blog.service.BlogService;
import com.albert.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping({"/index","/",""})
    public ModelAndView index(BlogQuery condition, @RequestParam(value = "page", defaultValue = "1") Integer page){
        return new ModelAndView("admin/blog/index")
                .addObject("condition",condition)
                .addObject("pager",this.blogService.list(condition,page))
                .addObject("category",this.categoryService.listCategory());

    }

    @GetMapping("/append")
    public String append(Model model){
        model.addAttribute("blog",new Blog());
        model.addAttribute("category",this.categoryService.listCategory());
        return "admin/blog/append";
    }

    @PostMapping("/append")
    public ModelAndView append(Blog blog, HttpSession session){
        // 新增的时候需要传递user对象
        blog.setUser((User) session.getAttribute("user"));
        // 设置blog中的分类
        blog.setCategory(this.categoryService.detail(blog.getCategory().getId()));
        // 设置blog中的分类ID
        blog.setCategoryId(blog.getCategory().getId());
        // 设置blog中的用户ID
        blog.setUserId(blog.getUser().getId());

        if(this.blogService.append(blog)){
            return new ModelAndView("redirect:/admin/blog")
                    .addObject("message","添加成功");
        }else {
            return new ModelAndView("redirect:/admin/blog")
                    .addObject("message","添加失败");
        }
    }

    @GetMapping("/remove")
    public String remove(int id){
        this.blogService.remove(id);
        return "redirect:/admin/blog";
    }

    @GetMapping("/modify")
    public ModelAndView modify(Integer id){
        return new ModelAndView("admin/blog/modify")
                .addObject("blog",this.blogService.detail(id))
                .addObject("category",this.categoryService.listCategory());
    }

    @PostMapping("/modify")
    public String modify(Blog blog){
        this.blogService.modify(blog);
        return "redirect:/admin/blog";
    }

}
