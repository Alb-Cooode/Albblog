package com.albert.blog.mapper;

import com.albert.blog.pojo.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 相册数据访问接口
 */
@Mapper
public interface PictureMapper {

    /**
     * 查询所有相片
     * @return 相片列表
     */
    public List<Picture> getAllPicture();

    /**
     * 根据ID查询图片
     * @param id 图片ID
     * @return 图片对象
     */
    public Picture getPictureById(int id);

    /**
     * 插入图片
     * @param picture 图片对象
     * @return 受影响的行数
     */
    public int insert(Picture picture);

    /**
     * 根据ID删除图片
     * @param id 图片ID
     * @return 受影响的行数
     */
    public int delete(int id);

    /**
     * 修改图片信息
     * @param picture 图片对象
     * @return 受影响的行数
     */
    public int update(Picture picture);

    /**
     * 根据条件分页查询图片
     * @param condition 图片条件
     * @param skip 跳过的记录数
     * @param take 取的记录数
     * @return 图片集合
     */
    public List<Picture> getPictureByCondition(@Param("condition") Picture condition, @Param("skip") int skip, @Param("take") int take);

    /**
     * 获取图片数量
     * @param condition 图片条件
     * @return 图片数量
     */
    public int size(@Param("condition") Picture condition);

    /**
     * 判断是否已经存在该图片
     * @param pictureAddress 图片地址
     * @return 该图片的数量
     */
    public int isExistByPictureAddress(String pictureAddress);

}
