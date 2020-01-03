package com.example.demo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

@ApiModel(value = "com-example-demo-domain-Note")
@Data
public class Note {
    @ApiModelProperty(value = "null")
    private String id;

    @ApiModelProperty(value = "null")
    private String title;

    @ApiModelProperty(value = "null")
    private String text;

    @ApiModelProperty(value = "null")
    private Integer isDel;

    @ApiModelProperty(value = "null")
    private String createBy;

    @ApiModelProperty(value = "null")
    private String updateBy;

    @ApiModelProperty(value = "null")
    private Date createTime;

    @ApiModelProperty(value = "null")
    private Date updateTime;

    @ApiModelProperty(value = "null")
    private String description;
}