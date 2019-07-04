package com.minqing.demo.controller;

import com.minqing.demo.service.PaperstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
public class Download {
    private static final String EXTENSION = ".doc";
    private static final String SERVER_LOCATION = "D:\\lunwen\\";
    @Autowired
    private PaperstateService paperstateService;

    @RequestMapping("/passPaper")
    public void passPaper(@RequestBody Map<String, String> m) {
        String studentid = m.get("studentid");
        paperstateService.passPaperstate(studentid);
    }

    @RequestMapping("/refusePaper")
    public void refusePaper(@RequestBody Map<String, String> m) {
        String studentid = m.get("studentid");
        paperstateService.refusePaperstate(studentid);
    }


    @RequestMapping("/download/{userid}")
    public ResponseEntity<Object> downloadbyuser(@PathVariable("userid") String userid) throws IOException {
        File file = new File(SERVER_LOCATION + userid + EXTENSION);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + userid + ".doc");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}

        // @RequestMapping("/hasuploaded")
        // public int hasuploaded(@RequestBody Map<String,String> m)
        // {
        //     return paperstateService.hasUploaded(m.get("studentid"));
        // }
//    @RequestMapping(path = "/download/{userid}")
//    public Object download(@PathVariable("userid") String userid) throws IOException {
//        if(paperstateService.hasUploaded(userid)==1) {
//            File file = new File(SERVER_LOCATION + userid + EXTENSION);
//
//            HttpHeaders header = new HttpHeaders();
//            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + userid + ".doc");
//            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
//            header.add("Pragma", "no-cache");
//            header.add("Expires", "0");
//
//            Path path = Paths.get(file.getAbsolutePath());
//            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//
//            return ResponseEntity.ok()
//                    .headers(header)
//                    .contentLength(file.length())
//                    .contentType(MediaType.parseMediaType("application/octet-stream"))
//                    .body(resource);
//
//        }
//        else{
//                return ResponseEntity.
//                status(HttpStatus.PERMANENT_REDIRECT)
//                .header("location","192.168.101.44:8080")
//                .contentType(MediaType.TEXT_HTML)
//                .body(null);
//        }
//    }

