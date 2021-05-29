package com.albert.blog.controller.admin;

import com.albert.blog.pojo.Picture;
import com.albert.blog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 相册控制层
 */
@Controller
@RequestMapping("/admin/picture")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping({"/index","","/"})
    public ModelAndView index(Picture condition, @RequestParam(value = "page", defaultValue = "1") Integer page){
        return new ModelAndView("admin/picture/index")
                .addObject("pager",this.pictureService.list(condition,page))
                .addObject("condition",condition);
    }

    @GetMapping("/append")
    public String append(){
        return "/admin/picture/append";
    }

    @PostMapping("/append")
    public String append(Picture picture){
        this.pictureService.append(picture);
        return "redirect:/admin/picture";
    }

    @GetMapping("/remove")
    public String remove(int id){
        this.pictureService.remove(id);
        return "redirect:/admin/picture";
    }

    @GetMapping("/modify")
    public ModelAndView modify(int id){
        return new ModelAndView("admin/picture/modify")
                .addObject("picture",this.pictureService.detail(id));
    }

    @PostMapping("/modify")
    public String modify(Picture picture){
        this.pictureService.modify(picture);
        return "redirect:/admin/picture";
    }

    @ResponseBody
    @PostMapping("/isExistByPictureAddress")
    public boolean isExistByPictureAddress(String pictureAddress){
        return this.pictureService.isExistByPictureAddress(pictureAddress);
    }

}
