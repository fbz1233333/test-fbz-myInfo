package com.example.demo.controllerV;

import com.example.demo.annotiation.UserLoginToken;
import com.example.demo.exception.TokenConfirmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v0")
public class v0Controller {

    static Logger logger = LoggerFactory.getLogger(v0Controller.class);

    @Autowired
    HttpServletRequest request;
//
//    @GetMapping("token")
//    @UserLoginToken
//    public String test1(HttpServletRequest request){
//        String LOGIN_USER_ID=request.getHeader("LOGIN_USER_ID");
//        logger.info("USER_LOGIN_ID:{}",LOGIN_USER_ID);
//        String str="success";
//        return  str;
//    }

    @GetMapping("token")
    @UserLoginToken
    @CrossOrigin
    public void test2(){
        logger.info("验证成功");
    }


    @GetMapping("NoToken")
    @CrossOrigin
    public void NoToken() throws TokenConfirmException {
        logger.info("CONTROLLER验证失败");

        throw new TokenConfirmException();
//        HashMap<String ,Object> map=new HashMap<>();
//        map.put("errorInfo","NO_TOKEN");
//        return map;
//        throw  new Exception()
    }




    @GetMapping("test")
    @CrossOrigin
    public ResponseEntity<HashMap<String,Object>> ge(){
        HashMap<String ,Object> map=new HashMap<>();
        map.put("result","info");
        map.put("text","info");

        ResponseEntity<HashMap<String, Object>> responseEntity = new ResponseEntity<>(map,HttpStatus.OK);
        logger.info("headers:{}",request.getHeader("LOGIN_USER_ID"));
        logger.error("返回体为:{}",responseEntity);
        return responseEntity;
    }


}
