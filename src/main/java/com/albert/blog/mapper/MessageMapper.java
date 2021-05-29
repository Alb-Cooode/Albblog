package com.albert.blog.mapper;

import com.albert.blog.pojo.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 留言数据访问接口
 */
@Mapper
public interface MessageMapper {

    /**
     * 留言总数
     * @return 留言总数
     */
    public int sumOfMessage();

    /**
     * 查询顶层留言，即父留言ID为-1
     * @return 留言集合
     */
    public List<Message> selectAllNotParent();

    /**
     * 根据顶层留言ID查询回复ID
     * @param topMessageId 顶层留言ID
     * @return 留言集合
     */
    public List<Message> selectFirstReply(int topMessageId);

    /**
     * 根据第一层留言回复ID查询子层留言
     * @param firstMessageId 第一层留言回复ID
     * @return 第二层回复留言集合
     */
    public List<Message> selectSecondReply(int firstMessageId);

    /**
     * 根据父留言ID查询留言
     * @param parentMessageId 父评论ID
     * @return 留言
     */
    public Message selectByParentId(int parentMessageId);

    /**
     * 新增留言
     * @param message 留言对象
     * @return 受影响的行数
     */
    public int insert(Message message);

}
