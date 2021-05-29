package com.albert.blog.mapper;

import com.albert.blog.pojo.Blog;
import com.albert.blog.pojo.BlogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 博客数据访问接口
 */
@Mapper
public interface BlogMapper {

    /**
     * 新增博客
     * @param blog 博客对象
     * @return 操作影响列数量
     */
    public int insert(Blog blog);

    /**
     * 根据ID删除博客
     * @param id 博客ID
     * @return 操作影响列数量
     */
    public int delete(int id);

    /**
     * 修改博客
     * @param blog 博客对象
     * @return 操作影响列数量
     */
    public int update(Blog blog);

    /**
     * 根据ID查博客
     * @param id 博客ID
     * @return 博客对象
     */
    public Blog getBlogById(int id);

    /**
     * 分页查询所有已发布的博客
     * @param skip 跳过的记录数
     * @param take 获取的记录数
     * @return 分页后的博客列表
     */
    public List<Blog> listBlogs(@Param("skip") int skip, @Param("take") int take);

    /**
     * 获取所有的已发布的博客
     * @return 博客集合
     */
    public List<Blog> listAll();

    /**
     * 根据条件查询博客
     * @param condition 条件
     * @param skip 跳过的记录数
     * @param take 获取的记录数
     * @return 博客列表
     */
    public List<Blog> listBlogsByCondition(@Param("condition") Blog condition, @Param("skip") Integer skip, @Param("take") Integer take);

    /**
     * 根据文本内容查询博客
     * @param text 查询的文本内容
     * @param skip 跳过的记录数
     * @param take 获取的记录数
     * @return 博客列表
     */
    public List<Blog> searchBlog(@Param("text") String text, @Param("skip") int skip, @Param("take") int take);

    /**
     * 查询受推荐的博客
     * @return 受推荐的博客列表集合
     */
    public List<Blog> listByRecommend();

    /**
     * 根据条件查询博客(涉及联表查询)
     * @param condition 条件
     * @param skip 跳过的记录数
     * @param take 获取的记录数
     * @return 博客查询列表
     */
    public List<BlogQuery> getBlogQueryByCondition(@Param("condition") BlogQuery condition, @Param("skip") int skip, @Param("take") int take);

    /**
     * 根据条件查询博客的数量
     * @param condition 条件
     * @return 数量
     */
    public int sizeByCondition(@Param("condition") BlogQuery condition);

    /**
     * 根据条件查询博客的数量
     * @param condition 条件
     * @return 数量
     */
    public int sizeByBlogCondition(@Param("condition") Blog condition);

    /**
     * 根据内容条件查询博客的数量
     * @param text 内容
     * @return 数量
     */
    public int sizeByText(@Param("text") String text);

    /**
     * 所有博客数
     * @return 所有博客数量
     */
    public int size();

    /**
     * 查询已发博客的总数
     * @return 已发博客的数量
     */
    public int sumOfBlogs();

    /**
     * 查询访问总数
     * @return 访问总数
     */
    public int sumOfViews();

    /**
     * 查询该博客下的所有评论
     * @param id 博客ID
     * @return 评论集合
     */
    public int commentNumById(int id);


}
