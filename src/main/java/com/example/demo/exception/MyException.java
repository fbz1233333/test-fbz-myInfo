package com.example.demo.exception;

import lombok.Data;

@Data
public class MyException extends RuntimeException {

    public  MyException(){}

    public MyException(String s){
        super(s);
    }
    public MyException(String s,Throwable cause){
        super(s,cause);
    }
}
