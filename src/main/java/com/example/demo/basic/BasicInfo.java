package com.example.demo.basic;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
@Data
public class BasicInfo {

    Integer isDel;

    String updateBy;

    String createBy;

    Date createTime;

    Date updateTime;

}
