package com.minqing.demo.controller;

import com.minqing.demo.service.PaperstateService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
public class Download {
    private static final String EXTENSION = ".doc";
    private static final String SERVER_LOCATION = "D:\\lunwen\\";
    private PaperstateService paperstateService;

    @RequestMapping("/passPaper")
    public void passPaper(@RequestBody Map<String,String> m)
    {
        String studentid=m.get("studentid");
        paperstateService.passPaperstate(studentid);
    }

    @RequestMapping("refusePaper")
    public void refusePaper(@RequestBody Map<String,String> m)
    {
        String studentid=m.get("studentid");
        paperstateService.refusePaperstate(studentid);
    }








    @RequestMapping(path = "/download")
    public Object download(@RequestBody Map<String,String> m) throws IOException {
        if(paperstateService.hasUploaded(m.get("studentid"))==1) {
            File file = new File(SERVER_LOCATION + m.get("studentid") + EXTENSION);

            HttpHeaders header = new HttpHeaders();
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + m.get("studentid") + ".doc");
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
        else{
            return 0;

        }
    }
}
