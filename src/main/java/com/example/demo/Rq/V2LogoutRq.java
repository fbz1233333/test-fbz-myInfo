package com.example.demo.Rq;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class V2LogoutRq {

    @NotNull(message = "注销用户状态错误")
    @Size(min = 36,max = 36,message = "注销用户id错误")
    String id;
}
