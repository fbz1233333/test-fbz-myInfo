package com.example.demo.exceptionInfo;

import lombok.Data;

import java.util.List;

@Data
public class ExceptionBaseInfo {

    Integer code;

    String type;

    String info;

    public ExceptionBaseInfo(Integer code, String info){
        this.code=code;
        this.type=type;
        this.info=info;
    }

    public ExceptionBaseInfo(){

    }




}
