package com.example.demo.controller;

import com.example.demo.Rq.FileDeleteRq;
import com.example.demo.controllerV.v0Controller;
import feign.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/file")
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(v0Controller.class);


    @Value("${file.image.path}")
    String IMAGE_PATH;

    @PostMapping(value = "delete")
    public void delete(@RequestBody FileDeleteRq fileDeleteRq){
        String fileName=fileDeleteRq.getFileName();
        String fileType=fileDeleteRq.getFileType();
        String path="";
        if ("IMAGE".equals(fileType)){
            path=IMAGE_PATH;
        }
        File file1=new File(path+fileName);
        file1.delete();
    }

}
