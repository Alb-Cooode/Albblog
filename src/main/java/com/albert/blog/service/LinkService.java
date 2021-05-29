package com.albert.blog.service;

import com.albert.blog.pojo.Link;
import com.albert.blog.utils.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 友链业务逻辑接口
 */
public interface LinkService {

    /**
     * 查询所有友链
     * @return 友链列表
     */
    public List<Link> listLink();

    /**
     * 友链详情
     * @param id 友链ID
     * @return 友链对象
     */
    public Link detail(int id);

    /**
     * 添加友链
     * @param link 友链对象
     * @return 布尔值
     */
    public boolean append(Link link);

    /**
     * 修改友链信息
     * @param link 友链对象
     * @return 布尔值
     */
    public boolean modify(Link link);

    /**
     * 根据ID删除友链
     * @param id 分类ID
     * @return 布尔值
     */
    public boolean remove(int id);

    /**
     * 判断该友链地址是否已存在
     * @param blogAddress 友链地址
     * @return 布尔值
     */
    public boolean isExistByBlogAddress(String blogAddress);

    /**
     * 根据条件分页查询
     * @param condition 条件
     * @param page 页码数
     * @return Pager类型的列表
     */
    public Pager<Link> list(@Param("condition") Link condition, @Param("page") Integer page);

}
