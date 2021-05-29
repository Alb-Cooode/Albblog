package com.albert.blog.controller.admin;

import com.albert.blog.pojo.Link;
import com.albert.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 友链控制层
 */
@Controller
@RequestMapping("/admin/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @RequestMapping({"/index","","/"})
    public ModelAndView index(Link condition, @RequestParam(value = "page", defaultValue = "1") Integer page){
        return new ModelAndView("admin/link/index")
                .addObject("condition",condition)
                .addObject("pager",this.linkService.list(condition,page));
    }

    @GetMapping("/append")
    public String append(){
        return "/admin/link/append";
    }

    @PostMapping("/append")
    public String append(Link link){
        this.linkService.append(link);
        return "redirect:/admin/link/";
    }

    @GetMapping("remove")
    public String remove(int id){
        this.linkService.remove(id);
        return "redirect:/admin/link";
    }

    @GetMapping("/modify")
    public ModelAndView modify(int id){
        return new ModelAndView("admin/link/modify")
                .addObject("link",this.linkService.detail(id));
    }

    @PostMapping("/modify")
    public String modify(Link link){
        this.linkService.modify(link);
        return "redirect:/admin/link";
    }

    @ResponseBody
    @PostMapping("/isExistByBlogAddress")
    public boolean isExistByBlogAddress(String blogAddress){
        return this.linkService.isExistByBlogAddress(blogAddress);
    }

}
