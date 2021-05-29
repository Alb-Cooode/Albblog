package com.albert.blog.controller;

import com.albert.blog.pojo.Message;
import com.albert.blog.pojo.User;
import com.albert.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 留言控制层
 */
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Value("${messages.img}")
    private String messageAva;

    // 跳转到留言页面
    @GetMapping("/message")
    public String message(){
        return "message";
    }

    // 跳转到message页面中message模块
    @GetMapping("/messageList")
    public ModelAndView messageList(){
        return new ModelAndView("message::messageList")
                .addObject("messages",this.messageService.listMessage());
    }

    // 新增留言
    @PostMapping("/message")
    public ModelAndView append(HttpSession session, Message message){
        User user = (User)session.getAttribute("user");
        if(user != null){
            message.setAvatar(user.getAvatar());
            message.setAdminMessage(true);
        }else {
            message.setAvatar(messageAva);
        }
        this.messageService.append(message);
        return new ModelAndView("message::messageList")
                .addObject("messages",this.messageService.listMessage());
    }

}
