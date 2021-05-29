package com.albert.blog.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类实体类
 * 由于分类和博客属于一对多的关系，所以在实体类的定义中不止需要定义分类ID和分类名，还需要定义博客集合来在mybatis中实现现出一对多的查询
 */
public class Category {

    private int id;

    private String catName;

    private int blogSize;

    private List<Blog> blogs = new ArrayList<>();

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", catName='" + catName + '\'' +
                ", blogs=" + blogs +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getBlogSize() {
        return blogSize;
    }

    public void setBlogSize(int blogSize) {
        this.blogSize = blogSize;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
