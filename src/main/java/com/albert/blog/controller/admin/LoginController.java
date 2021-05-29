package com.albert.blog.controller.admin;

import com.albert.blog.pojo.User;
import com.albert.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     *  跳转到登录页面
     * @return 返回登录页面
     */
    @GetMapping({"/login","","/"})
    public String login(){
        return "admin/login";
    }

    /**
     * 校验登录信息
     * @param username 用户名
     * @param password 密码
     * @param session session域
     * @return 登陆成功则返回index页面，否则重新返回登录界面
     */
    @PostMapping("/login")
    public ModelAndView login(String username, String password, HttpSession session, RedirectAttributes attributes){
        User user = this.userService.loginByUsernameAndPassword(username,password);
        if(user != null){
            session.setAttribute("user",user);
            return new ModelAndView("redirect:/admin/index");
        }else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return new ModelAndView("redirect:/admin");
        }
    }

    @GetMapping("/index")
    public String index(){
        return "admin/index";
    }

    /**
     * 退出登录
     * @param session session作用域
     * @return 返回登录页面
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
