package com.example.demo.controller;

import com.example.demo.dao.ResumeMapper;
import com.example.demo.domain.Resume;
import com.example.demo.results.FindIdAndNameByNameAndPasswordAndIsDelResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    ResumeMapper resumeMapper;

    @GetMapping("{id}")
    public Resume get(@PathVariable String  id){
        return resumeMapper.selectByPrimaryKey(id);
    }

    @PostMapping
    public void add(@RequestBody Resume resume){
        resumeMapper.insertSelective(resume);
    }

    @PutMapping
    public void update(@RequestBody Resume resume){
        resumeMapper.updateByPrimaryKeySelective(resume);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        resumeMapper.deleteByPrimaryKey(id);
    }

    @GetMapping("my")
    public Resume get(){
        return resumeMapper.find();
    }
}
