package com.example.demo.exception;

public class TokenConfirmException extends Exception {
    public TokenConfirmException(){}

    public TokenConfirmException(String s){
        super(s);
    }
    public TokenConfirmException(String s,Throwable cause){
        super(s,cause);
    }
}
