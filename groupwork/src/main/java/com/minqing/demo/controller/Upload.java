package com.minqing.demo.controller;


import com.minqing.demo.service.PaperstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
public class Upload {
    @Autowired
    private PaperstateService paperstateService;



    @RequestMapping("/showStudentHasUploaded")
    public int showStudentHasUploaded(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        String userid = "";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userid")){
                userid = cookie.getValue();
            }
        }
       return paperstateService.hasUploaded(userid);
    }



    @RequestMapping("/uploadfile")
    public void uploadfile(@RequestParam(value = "file",required = false) MultipartFile file, HttpServletRequest request) {
        if (file != null && !file.isEmpty()) {
            try {
                Cookie[] cookies = request.getCookies();
                String userid = "";
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals("userid")){
                        userid = cookie.getValue();
                    }
                }
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("D:\\lunwen\\" + userid + ".doc")));//保存图片到目录下
                out.write(file.getBytes());
                out.flush();//输出流的方法，清空缓冲区
                out.close();
                paperstateService.initPaper(userid);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
