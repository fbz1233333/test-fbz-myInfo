package com.example.demo.Rq;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class V1ArticleRq {

    @NotNull(message = "请求ID不能为空")
    @Size(min=36,max = 36,message = "请求ID格式错误")
    String id;
}
