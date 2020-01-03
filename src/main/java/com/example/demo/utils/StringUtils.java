package com.example.demo.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StringUtils {
    public List<String> changeToList(String origin){
        List<String> list=new ArrayList<>();
        String s2=origin.substring(1,origin.length()-1);
        return Arrays.asList(s2.split(","));
    }
}
