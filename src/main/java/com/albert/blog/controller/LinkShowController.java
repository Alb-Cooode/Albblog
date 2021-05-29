package com.albert.blog.controller;

import com.albert.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 友链前台展示控制层
 */
@Controller
public class LinkShowController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/link")
    public ModelAndView link(){
        return new ModelAndView("link")
                .addObject("links",this.linkService.listLink());
    }

}
