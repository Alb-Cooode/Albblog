package com.albert.blog.mapper;

import com.albert.blog.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分类数据访问接口
 */
@Mapper
public interface CategoryMapper {

    /**
     * 查询所有分类
     * @return 分类列表
     */
    public List<Category> getAllCategoty();

    /**
     * 根据ID查询分类
     * @param id 分类ID
     * @return 分类对象
     */
    public Category getCategoryById(int id);

    /**
     * 插入分类
     * @param category 分类对象
     * @return 受影响的行数
     */
    public int insert(Category category);

    /**
     * 根据ID删除分类
     * @param id 分类ID
     * @return 受影响的行数
     */
    public int delete(int id);

    /**
     * 修改分类信息
     * @param category 分类对象
     * @return 受影响的行数
     */
    public int update(Category category);

    /**
     * 根据条件分页查询分类
     * @param condition 分类条件
     * @param skip 跳过的记录数
     * @param take 取的记录数
     * @return 分类集合
     */
    public List<Category> getCategoryByCondition(@Param("condition") Category condition, @Param("skip") int skip, @Param("take") int take);

    /**
     * 获取分类数量
     * @param condition 分类条件
     * @return 分类数量
     */
    public int size(@Param("condition") Category condition);

    /**
     * 判断是否已经存在该分类名
     * @param catName 分类名
     * @return 该分类名的数量
     */
    public int isExistByCatName(String catName);

    /**
     * 判断该分类下是否存在博客
     * @param id 分类ID
     * @return 博客的数量
     */
    public int isUsedByBlog(int id);

}
