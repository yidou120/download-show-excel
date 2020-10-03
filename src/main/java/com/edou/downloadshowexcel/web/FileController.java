package com.edou.downloadshowexcel.web;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @ClassName FileController
 * @Description 文件下载生成控制器
 * @Author 中森明菜
 * @Date 2020/9/30 14:08
 * @Version 1.0
 */
@Controller
@RequestMapping("/file")
public class FileController {
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @PostMapping(value = "/download")
    public void download(HttpServletResponse response){
        InputStream inputStream = null;
        OutputStream os = null;
        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024];
        try {
            os = response.getOutputStream();
            ClassPathResource classPathResource = new ClassPathResource("static/测试.xlsx");
            inputStream = classPathResource.getInputStream();
            response.setContentType("application/x-msdownload;charset=UTF-8");
            response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode("测试","UTF-8"));
            bis = new BufferedInputStream(inputStream);
            int i = bis.read(buffer);
            while(i != -1){
                os.write(buffer);
                i = bis.read(buffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
