package com.albert.blog.controller;

import com.albert.blog.pojo.Blog;
import com.albert.blog.pojo.Category;
import com.albert.blog.service.BlogService;
import com.albert.blog.service.CategoryService;
import com.albert.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 分类展示前台控制层
 */
@Controller
public class CategoryShowController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/category")
    public ModelAndView category(@RequestParam(value = "page",defaultValue = "1") Integer page,Integer categoryId){
        List<Category> categories = this.categoryService.listCategory();
        // 从首页传递的分类ID为-1，则显示第一个标签下的所有博客
        if (categoryId == -1){
            categoryId = categories.get(0).getId();
        }

        for (Category category : categories) {
            Blog conditionCatId = new Blog();
            conditionCatId.setCategoryId(category.getId());
            category.setBlogSize(this.blogService.sizeByCategoryId(conditionCatId));
        }

        Blog condition = new Blog();
        condition.setCategoryId(categoryId);
        condition.setPublished(true);

        return new ModelAndView("category")
                .addObject("pager",this.blogService.listByCondition(condition,page))
                .addObject("user",this.userService.selectById(1))
                .addObject("activeByCategoryId",categoryId)
                .addObject("categories",categories);
    }
}
