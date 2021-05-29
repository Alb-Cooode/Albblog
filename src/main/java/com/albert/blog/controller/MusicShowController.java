package com.albert.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 音乐盒前台展示控制层
 */
@Controller
public class MusicShowController {

    @GetMapping("/music")
    public String music(){
        return "music";
    }

}
