package com.example.demo.exceptionInfo;

import lombok.Data;
import org.omg.CORBA.INTERNAL;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ExceptionInfo {

    Integer code;

    String type;

    List<String> Infos;

    public ExceptionInfo(Integer code,String type,List<String > Infos){
        this.code=code;
        this.type=type;
        this.Infos=Infos;
    }

    public ExceptionInfo(){

    }




}
