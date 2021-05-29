package com.albert.blog.service.serviceImpl;

import com.albert.blog.mapper.BlogMapper;
import com.albert.blog.mapper.MessageMapper;
import com.albert.blog.pojo.Blog;
import com.albert.blog.pojo.Message;
import com.albert.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论业务逻辑实现类
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    // 存放迭代出的所有子留言集合
    private List<Message> tempReplys = new ArrayList<>();

    @Override
    public boolean append(Message message) {
        // 如果parentMessageId != -1即代表其有父亲
        if (message.getParentMessageId() != -1){
            message.setParentMessage(this.messageMapper.selectByParentId(message.getParentMessageId()));
        }else {
            message.setParentMessage(null);
        }

        return this.messageMapper.insert(message) > 0 ? true : false;
    }

    @Override
    public List<Message> listMessage() {

        // 查询顶层留言
        List<Message> messages = this.messageMapper.selectAllNotParent();

        for (Message message : messages) {
            Integer topMessageId = message.getId();
            String parentNickname = message.getNickname();
            // 一级回复集合
            List<Message> firstReplies = this.messageMapper.selectFirstReply(topMessageId);
            // 查询一级回复
            combineFirstReply(firstReplies,parentNickname);
            // 将遍历结果存放至回复中
            message.setReplyMessages(tempReplys);
            // 遍历结束将tempReplys初始化
            tempReplys = new ArrayList<>();

        }

        return messages;
    }

    public void combineFirstReply(List<Message> firstReplies, String parentNickname){
        // 判断是否有一级子评论
        if (firstReplies.size() > 0){
            // 循环找出子评论的ID
            for (Message firstReply : firstReplies) {
                Integer firstReplyId = firstReply.getId();
                String firstNickname = firstReply.getNickname();
                // 设置一级回复的父昵称
                firstReply.setParentNickname(parentNickname);
                firstReply.setNickname(firstNickname);
                tempReplys.add(firstReply);
                // 查询二级回复
                combineSecondReply(firstReplyId,firstNickname);
            }
        }
    }

    public void combineSecondReply(int firstReplyId, String firstNickname){
        // 根据一级回复Id找二级回复
        List<Message> secondReplys = this.messageMapper.selectSecondReply(firstReplyId);
        // 判断是否有二级回复
        if (secondReplys.size() > 0){
            for (Message secondReply : secondReplys) {
                Integer secondReplyId = secondReply.getId();
                String secondNickname = secondReply.getNickname();
                // 设置二级回复的父昵称
                secondReply.setParentNickname(firstNickname);
                secondReply.setNickname(secondNickname);
                tempReplys.add(secondReply);
                // 查询二级回复
                combineSecondReply(secondReplyId, secondNickname);
            }
        }
    }

}
