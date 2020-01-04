package com.example.demo.controllerV;

import com.example.demo.Rq.V2LoginRq;
import com.example.demo.Rq.V2LogoutRq;
import com.example.demo.Rq.V2RegisterRq;
import com.example.demo.annotiation.UserLoginToken;
import com.example.demo.domain.Resume;
import com.example.demo.domain.User;
import com.example.demo.feign.IFeignClient;
import com.example.demo.results.FindIdAndNameByNameAndPasswordAndIsDelResult;
import com.example.demo.utils.RedisUtil;
import com.example.demo.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class v1Controller {

    static Logger logger = LoggerFactory.getLogger(v1Controller.class);

    @Autowired
    IFeignClient iFeignClient;


    @GetMapping("getInfo")
    public Resume get(){
        return iFeignClient.resumeMine();
    }

    @PostMapping("update")
    @UserLoginToken
    public void update(@RequestBody Resume resume){
//        logger.info("传参为：{}",resume);
        iFeignClient.resumeUpdate(resume);
    }
}
