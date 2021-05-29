package com.albert.blog.service;

import com.albert.blog.pojo.Picture;
import com.albert.blog.utils.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 相册业务逻辑接口
 */
public interface PictureService {

    /**
     * 查询所有相册
     * @return
     */
    public List<Picture> listPicture();

    /**
     * 图片详情
     * @param id 图片ID
     * @return 图片对象
     */
    public Picture detail(int id);

    /**
     * 新增图片
     * @param picture 图片对象
     * @return 布尔值
     */
    public boolean append(Picture picture);

    /**
     * 删除图片
     * @param id 图片ID
     * @return 布尔值
     */
    public boolean remove(int id);

    /**
     * 修改图片信息
     * @param picture 图片ID
     * @return 布尔值
     */
    public boolean modify(Picture picture);

    /**
     * 根据条件分页查询图片
     * @param condition 条件
     * @param page 页数
     * @return 分页图片列表
     */
    public Pager<Picture> list(@Param("condition") Picture condition, @Param("page") Integer page);

    /**
     * 判断是否已存在该图片
     * @param pictureAddress 图片地址
     * @return 布尔值
     */
    public boolean isExistByPictureAddress(String pictureAddress);
}
