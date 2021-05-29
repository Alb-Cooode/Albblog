package com.albert.blog.controller.admin;

import com.albert.blog.pojo.Category;
import com.albert.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 分页查询分类列表
    @RequestMapping({"/index","/",""})
    public ModelAndView index(Category condition, @RequestParam(value = "page", defaultValue = "1")Integer page){
        return new ModelAndView("admin/category/index")
                .addObject("condition",condition)
                .addObject("pager",this.categoryService.list(condition,page));
    }

    // 跳转到新增分类页面
    @GetMapping("/append")
    public String append(){
        return "admin/category/append";
    }

    // 新增页面
    @PostMapping("/append")
    public String append(Category category){
        this.categoryService.append(category);
        return "redirect:/admin/category";
    }

    // 跳转到修改页面
    @GetMapping("/modify")
    public ModelAndView modify(Integer id){
        return new ModelAndView("admin/category/modify")
                .addObject("category",this.categoryService.detail(id));
    }

    // 修改页面
    @PostMapping("/modify")
    public String modify(Category category){
        this.categoryService.modify(category);
        return "redirect:/admin/category";
    }

    // 删除页面
    @GetMapping("/remove")
    public String remove(Integer id){
        this.categoryService.remove(id);
        return "redirect:/admin/category";
    }

    @ResponseBody
    @PostMapping("/isExistByCatName")
    public boolean isExistByCatName(String catName){
        return this.categoryService.isExistByCatName(catName);
    }

    @ResponseBody
    @GetMapping("/isUsedByBlog")
    public boolean isUsedByBlog(int id){
        return this.categoryService.isUsedByBlog(id);
    }
}
