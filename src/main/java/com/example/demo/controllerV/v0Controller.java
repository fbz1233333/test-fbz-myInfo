package com.example.demo.controllerV;

import com.example.demo.annotiation.UserLoginToken;
import com.example.demo.dto.TestDto1;
import com.example.demo.exception.TokenConfirmException;
import com.example.demo.exception.UserIsNotAuthorException;
import com.example.demo.exceptionInfo.ExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v0")
@CrossOrigin
public class v0Controller {

    static Logger logger = LoggerFactory.getLogger(v0Controller.class);

    @Autowired
    HttpServletRequest request;
//
//    @GetMapping("token")
//    @UserLoginToken
//    public String test1(HttpServletRequest request){
//        String loginUserID=request.getHeader("loginUserID");
//        logger.info("USER_LOGIN_ID:{}",loginUserID);
//        String str="success";
//        return  str;
//    }

    @GetMapping("token")
    @UserLoginToken
    public void test2(){
        logger.info("验证成功");
    }


    @GetMapping("userIsNotAuthor")
    @CrossOrigin
    public ResponseEntity<ExceptionInfo> isNotAuthor() throws UserIsNotAuthorException {
        throw new UserIsNotAuthorException();
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

    @PostMapping("test2")
    public HashMap<String ,Object> get(@RequestBody TestDto1 noteDto3){
        logger.info("it is:{}",noteDto3.getId());
        HashMap<String ,Object> map=new HashMap<>();
        map.put("r",noteDto3.getId());
        return map;
    }

    @GetMapping("test")
    public ResponseEntity<HashMap<String,Object>> ge(){
        HashMap<String ,Object> map=new HashMap<>();
        map.put("result","info");
        map.put("text","info");

        logger.info("headersID:{}",request.getHeader("loginUserId"));
        logger.info("headersTOKEN:{}",request.getHeader("loginUserToken"));

        ResponseEntity<HashMap<String, Object>> responseEntity = new ResponseEntity<>(map,HttpStatus.OK);
        logger.error("返回体为:{}",responseEntity);

        return responseEntity;
    }


}
