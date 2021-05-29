package com.albert.blog.service;

import com.albert.blog.pojo.Blog;
import com.albert.blog.pojo.BlogQuery;
import com.albert.blog.utils.Pager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

/**
 * 博客业务逻辑接口
 */
public interface BlogService {

    /**
     * 新增博客
     * @param blog 博客对象
     * @return 布尔值
     */
    public boolean append(Blog blog);

    /**
     * 根据ID删除博客
     * @param id 博客ID
     * @return 布尔值
     */
    public boolean remove(int id);

    /**
     * 修改博客
     * @param blog 博客对象
     * @return 布尔值
     */
    public boolean modify(Blog blog);

    /**
     * 博客详情
     * @param id 博客ID
     * @return 博客对象
     */
    public Blog detail(int id);

    /**
     * 带有Markdown语法的博客详情
     * @param id 博客ID
     * @return 博客对象
     * @throws NotFoundException 抛出异常
     */
    public Blog detailWithMarkdown(int id) throws NotFoundException;

    /**
     *根据条件分页查询博客列表
     * @param condition 条件
     * @param page 页数
     * @return 分页查询博客列表
     */
    public Pager<BlogQuery> list(@Param("condition") BlogQuery condition, @Param("page") int page);

    /**
     * 根据条件分页查询博客列表
     * @param condition 条件
     * @param page 页数
     * @return 分页后的博客列表
     */
    public Pager<Blog> listByCondition(@Param("condition") Blog condition, @Param("page") int page);

    /**
     * 分页查询所有博客
     * @param page 页数
     * @return 分页后的博客集合
     */
    public Pager<Blog> list(@Param("page") int page);

    /**
     * 获取所有已发布的博客
     * @return 博客列表
     */
    public List<Blog> listAll();

    /**
     * 受推荐的博客
     * @return 受推荐的博客列表
     */
    public List<Blog> listByRecommend();

    /**
     * 根据查询内容分页查询博客
     * @param text 查询内容
     * @param page 页数
     * @return 博客列表
     */
    public Pager<Blog> listByText(@Param("text") String text, @Param("page") int page);

    /**
     * 根据分类ID查询博客数量
     * @param condition 条件(分类ID)
     * @return 该分类下的博客数量
     */
    public int sizeByCategoryId(@Param("condition") Blog condition);

    /**
     * 博客总数
     * @return 博客总数
     */
    public int sumOfBlog();

    /**
     * 评论总数
     * @return 评论总数
     */
    public int sumOfComment();

    /**
     * 观看总数
     * @return 观看总数
     */
    public int sumOfViews();

    /**
     * 留言总数
     * @return 留言总数
     */
    public int sumOfMessages();
}
