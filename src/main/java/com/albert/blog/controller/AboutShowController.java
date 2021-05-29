package com.albert.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 关于我 控制层
 */
@Controller
public class AboutShowController {

    // 跳转至关于页面
    @GetMapping("/about")
    public String about(){
        return "about";
    }

}
