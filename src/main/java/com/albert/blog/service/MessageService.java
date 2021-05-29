package com.albert.blog.service;

import com.albert.blog.pojo.Message;

import java.util.List;

/**
 * 留言业务逻辑接口
 */
public interface MessageService {

    /**
     * 新增留言
     * @param message 留言对象
     * @return 布尔值
     */
    public boolean append(Message message);

    /**
     * 查询留言列表
     * @return 留言列表
     */
    public List<Message> listMessage();
}
