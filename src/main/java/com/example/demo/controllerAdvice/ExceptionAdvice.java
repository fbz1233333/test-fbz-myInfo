package com.example.demo.controllerAdvice;

import com.example.demo.exception.MyException;
import com.example.demo.exception.TokenConfirmException;
import com.example.demo.exception.UserIsNotAuthorException;
import com.example.demo.exceptionInfo.ExceptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionAdvice{

    final static Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @Value("${error.unvalidated.code}")
    Integer ERROR_UNVALIDATED_CODE;

    @Value("${error.no_token.code}")
    Integer ERROR_NO_TOKEN_CODE;

    @Value("${error.non_null.code}")
    Integer ERROR_NON_NULL_CODE;


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionInfo> test3(MethodArgumentNotValidException e) {
//        logger.info("出现了验证错误了");
        logger.info("in MethodArgumentExceptionHandler:{}", e.getMessage());
        List<String> strings = new ArrayList<>();
        ExceptionInfo exceptionInfo = new ExceptionInfo();
        exceptionInfo.setType("VALIDATED_EXCEPTION");

        for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
            strings.add(objectError.getDefaultMessage());
        }
        ;
        exceptionInfo.setInfos(strings);
        exceptionInfo.setCode(ERROR_UNVALIDATED_CODE);
        return new ResponseEntity<ExceptionInfo>(exceptionInfo, HttpStatus.BAD_REQUEST);
    }
//
//    @ExceptionHandler(ArithmeticException.class)
//    public void test(ArithmeticException e){
//        logger.info("数字/ by zero错误");
//        logger.info("Message:{}",e.getMessage());
//    }

    @ExceptionHandler(TokenConfirmException.class)
    public ResponseEntity<ExceptionInfo> test2(TokenConfirmException e) {

        logger.info("进入NOTOKENExceptionHandler:{}", e.getMessage());
        ExceptionInfo exceptionInfo = new ExceptionInfo();
        String info = "you have not log in";
        List<String> strings = new ArrayList<>();
        strings.add(info);
        exceptionInfo.setInfos(strings);
        exceptionInfo.setType("NO_TOKEN_EXCEPTION");
        exceptionInfo.setCode(ERROR_NO_TOKEN_CODE);
        return new ResponseEntity<ExceptionInfo>(exceptionInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionInfo> handler3(IllegalArgumentException e) {
        logger.info("进入IllegalArgumentExceptionHandler");
        ExceptionInfo exceptionInfo = new ExceptionInfo();
        exceptionInfo.setType("ILLEGAL_ARGUMENTS");
        List<String> strings = new ArrayList<>();
        String info = "requestBody is illegal";

        strings.add(info);
        exceptionInfo.setInfos(strings);
        exceptionInfo.setCode(ERROR_NON_NULL_CODE);
        return new ResponseEntity<ExceptionInfo>(exceptionInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserIsNotAuthorException.class)
    public ResponseEntity<ExceptionInfo> isNotAuthor(){
        List<String > strings=new ArrayList<>();
        strings.add(isNotAuthorInfo);
        ExceptionInfo exceptionInfo=new ExceptionInfo(isNotAuthorCode,isNotAuthorType,strings);
        return new ResponseEntity<>(exceptionInfo,HttpStatus.BAD_REQUEST);
    }

    @Value("${error.is_not_author.info}")
    String isNotAuthorInfo;

    @Value("${error.is_not_author.code}")
    Integer isNotAuthorCode;

    @Value("${error.is_not_author.type}")
    String isNotAuthorType;

}
