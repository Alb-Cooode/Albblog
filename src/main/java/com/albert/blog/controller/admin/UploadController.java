package com.albert.blog.controller.admin;

import cn.hutool.json.JSONObject;
import com.albert.blog.utils.QiNiuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/img")
public class UploadController {

    @Autowired
    private QiNiuUtils qiNiuUtils;

    @RequestMapping("/imgUpload")
    public void imageUpload(@RequestParam(value = "editormd-image-file", required = true)MultipartFile[] files,
                            HttpServletRequest request, HttpServletResponse response){

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = null;
        JSONObject json = new JSONObject();

        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                try {
                    writer = response.getWriter();
                    FileInputStream fileInputStream = (FileInputStream) files[i].getInputStream();
                    String url = "http://" + qiNiuUtils.upLoadQiNiuYun(fileInputStream);
                    //输出url上传后的,可以复制url到浏览器访问
                    System.out.println("url=" + url);
                    json.set("success", 1);
                    json.set("message", "上传成功");
                    json.set("url", url);
                    try {
                        //延迟两秒让七牛云缓一下
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    json.set("success", 0);
                    json.set("message", "上传失败");
                    e.printStackTrace();
                } finally {
                    writer.print(json);
                    writer.flush();
                    writer.close();
                }
            }
        }

    }

}
