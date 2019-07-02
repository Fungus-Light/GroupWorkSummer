package com.minqing.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class Upload {
    @RequestMapping("/uploadfile")
    public void uploadfile(@RequestParam(value = "file",required = false) MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("D:\\lunwen\\" + "02016141223043" + ".doc")));//保存图片到目录下
                out.write(file.getBytes());
                out.flush();//输出流的方法，清空缓冲区
                out.close();
//                String filename = "D:\\UI" + username + ".jpg";
//                user.setUserIcon(filename);
//                userServiceImpl.InsertUser(user);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
