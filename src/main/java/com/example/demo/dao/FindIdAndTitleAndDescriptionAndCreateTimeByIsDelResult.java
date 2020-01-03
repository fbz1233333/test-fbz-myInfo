package com.example.demo.dao;

import lombok.Data;

import java.util.Date;

@Data
public class FindIdAndTitleAndDescriptionAndCreateTimeByIsDelResult {
    private String id;
    private String title;
    private String description;
    private Date createTime;
}
