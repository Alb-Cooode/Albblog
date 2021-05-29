package com.albert.blog.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

@Component
public class QiNiuUtils {
//配置里不能写带有-的
    private String accessKey="七牛云accessKey";
    private String secretKey="七牛云secretKey";
    private String bucket="七牛云空间名";
    private String path="个人域名";
    /**
     * 上传图片到七牛云
     *
     * @param file 图片文件
     * @return 字符串
     */
    public String upLoadQiNiuYun(FileInputStream file) {
        System.out.println("accessKey=" + accessKey);
        // zone0华东区域,zone1是华北区域,zone2是华南区域  注意一定要选择七牛云你创建空间选择的区域不然报错
        Configuration cfg = new Configuration(Zone.zone2());
        //功能可能会过时，但是是可以用的
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证，然后准备上传
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(file, null, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return path + "/" + putRet.key;
            } catch (QiniuException e) {
                Response r = e.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                }

            }
        }   catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
}

