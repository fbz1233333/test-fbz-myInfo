package com.example.demo.controller;

import com.example.demo.dao.FindIdAndNameAndHeadByIdAndIsDelResult;
import com.example.demo.results.FindIdAndNameByNameAndPasswordAndIsDelResult;
import com.example.demo.dao.UserMapper;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("{id}")
    public User get(@PathVariable String  id){
        return userMapper.selectByPrimaryKey(id);
    }

    @GetMapping("restricted/{id}")
    public FindIdAndNameAndHeadByIdAndIsDelResult find2(@PathVariable String id){
        return userMapper.findIdAndNameAndHeadByIdAndIsDel(id,0);
    }

    @PostMapping
    public void add(@RequestBody User user){
        userMapper.insertSelective(user);
    }

    @PutMapping
    public void update(@RequestBody User user){
        userMapper.updateByPrimaryKeySelective(user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        userMapper.deleteByPrimaryKey(id);
    }

    //登录查询
    @PostMapping("login")
    public FindIdAndNameByNameAndPasswordAndIsDelResult find1(@RequestBody User user){
        return userMapper.findIdAndNameByNameAndPasswordAndIsDel(user);
    }



}
