package com.example.demo.Rq;

import com.example.demo.basic.BasicInfo;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//哪些信息是补足的 那些信息是转化的
@Data
public class V2LoginRq extends BasicInfo {

    private String id;

    @NotNull(message = "请输入用户名")
    @Size(min = 3,max = 5,message = "用户名长度应在3-5之间")
    private String name;

    @NotNull(message = "请输入密码")
    @Size(min = 6,max = 8,message = "密码长度应在6-8之间")
    private String password;
}
