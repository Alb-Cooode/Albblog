package com.albert.blog.service;

import com.albert.blog.pojo.Category;
import com.albert.blog.utils.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分类业务逻辑接口
 */
public interface CategoryService {

    /**
     * 查询所有分类
     * @return 分类列表
     */
    public List<Category> listCategory();

    /**
     * 分类详情
     * @param id 分类ID
     * @return 分类对象
     */
    public Category detail(int id);

    /**
     * 添加分类
     * @param category 分类对象
     * @return 布尔值
     */
    public boolean append(Category category);

    /**
     * 修改分类信息
     * @param category 分类对象
     * @return 布尔值
     */
    public boolean modify(Category category);

    /**
     * 根据ID删除分类
     * @param id 分类ID
     * @return 布尔值
     */
    public boolean remove(int id);

    /**
     * 判断该分类名是否已存在
     * @param catName 分类名
     * @return 布尔值
     */
    public boolean isExistByCatName(String catName);

    /**
     * 判断该分类下是否存在博客
     * @param id 分类ID
     * @return 布尔值
     */
    public boolean isUsedByBlog(int id);

    /**
     * 根据条件分页查询
     * @param condition 条件
     * @param page 页码数
     * @return Pager类型的列表
     */
    public Pager<Category> list(@Param("condition") Category condition, @Param("page") Integer page);

}
