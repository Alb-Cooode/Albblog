package com.albert.blog.controller;

import com.albert.blog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 照片墙控制层
 */
@Controller
public class PictureShowController {

    @Autowired
    private PictureService pictureService;

    @GetMapping("/picture")
    public ModelAndView picture(){
        return new ModelAndView("picture")
                .addObject("pictures",this.pictureService.listPicture());
    }

}
