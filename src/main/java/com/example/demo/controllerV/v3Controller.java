package com.example.demo.controllerV;

import com.example.demo.Rq.FileDeleteRq;
import com.example.demo.annotiation.UserLoginToken;
import com.example.demo.domain.Resume;
import com.example.demo.feign.IFeignClient;
import com.example.demo.feign.UploadFeignClient;
import feign.RequestLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/v3")
public class v3Controller {

    static Logger logger = LoggerFactory.getLogger(v3Controller.class);

    @Autowired
    IFeignClient iFeignClient;

    @Autowired
    UploadFeignClient uploadFeignClient;


    @Value("${file.image.path}")
    String IMAGE_PATH;

    @Value("${file.default.path}")
    String DEFAULT_PATH;


    //用户在更新的时候的upload
    @PostMapping("upload")
    @UserLoginToken
    public HashMap<String,Object> upload(@RequestParam("file") MultipartFile file, @RequestParam("type") String  type){
        HashMap<String,Object> map=new HashMap<>();
        logger.info("fileType is:{}",type);
        String path;
        if ("IMAGE".equals(type)) {
            path = IMAGE_PATH;
        } else {
            path = DEFAULT_PATH;
        }
        path+=request.getHeader(loginUserIdTag)+'/'+noteImage;
        logger.info("need dir :{}" ,path);
        map.put("fileName",uploadFeignClient.upload(file,path));
        return   map;
    }
    @Autowired
    HttpServletRequest request;

    @Value("${headerInfo.loginUserId.name}")
    String loginUserIdTag;


    final String noteImage="noteImage/";

    @PostMapping("delete")
    @UserLoginToken
    public void delete(@RequestBody FileDeleteRq fileDeleteRq){
        String fileName=fileDeleteRq.getFileName();

        String loginUserId=request.getHeader(loginUserIdTag);
        File file1=new File(IMAGE_PATH+loginUserId+'/'+noteImage+fileName);
        if (!file1.exists()){
            logger.info("file is not exist:{}",file1);
        }else {
            file1.delete();
        }
    }
}
