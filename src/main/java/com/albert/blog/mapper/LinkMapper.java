package com.albert.blog.mapper;

import com.albert.blog.pojo.Link;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 友链数据访问接口
 */
@Mapper
public interface LinkMapper {

    /**
     * 查询所有友链
     * @return 友链列表
     */
    public List<Link> getAllLink();

    /**
     * 根据ID查询友链
     * @param id 友链ID
     * @return 友链对象
     */
    public Link getLinkById(int id);

    /**
     * 插入友链
     * @param link 友链对象
     * @return 受影响的行数
     */
    public int insert(Link link);

    /**
     * 根据ID删除友链
     * @param id 友链ID
     * @return 受影响的行数
     */
    public int delete(int id);

    /**
     * 修改友链信息
     * @param link 分类对象
     * @return 受影响的行数
     */
    public int update(Link link);

    /**
     * 根据条件分页查询友链
     * @param condition 友链条件
     * @param skip 跳过的记录数
     * @param take 取的记录数
     * @return 友链集合
     */
    public List<Link> getLinkByCondition(@Param("condition") Link condition, @Param("skip") int skip, @Param("take") int take);

    /**
     * 获取友链数量
     * @param condition 友链条件
     * @return 友链数量
     */
    public int size(@Param("condition") Link condition);

    /**
     * 判断是否已经存在该友链网址
     * @param blogAddress 友链网址
     * @return 该友链网址的数量
     */
    public int isExistByBlogAddress(String blogAddress);

}
