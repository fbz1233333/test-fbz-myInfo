package com.example.demo.controllerAdvice;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class AdviceTest2 {
    @InitBinder("pre")
    public void pre(WebDataBinder binder){
        binder.setFieldDefaultPrefix("pre.");
    }

    @InitBinder("head")
    public void head(WebDataBinder binder){
        binder.setFieldDefaultPrefix("head.");
    }

}
