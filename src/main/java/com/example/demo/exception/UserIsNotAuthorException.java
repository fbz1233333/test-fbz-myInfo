package com.example.demo.exception;

public class UserIsNotAuthorException extends Exception{
    public UserIsNotAuthorException(){}

    public UserIsNotAuthorException(String s){
        super(s);
    }
    public UserIsNotAuthorException(String s,Throwable cause){
        super(s,cause);
    }
}
