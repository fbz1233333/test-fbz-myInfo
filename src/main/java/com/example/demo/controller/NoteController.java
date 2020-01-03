package com.example.demo.controller;

import com.example.demo.dao.FindIdAndTitleAndDescriptionAndCreateTimeByIsDelResult;
import com.example.demo.dao.NoteMapper;
import com.example.demo.domain.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteMapper noteMapper;

    @GetMapping("{id}")
    public Note get(@PathVariable String  id){
        return noteMapper.selectByPrimaryKey(id);
    }

    @PostMapping
    public void add(@RequestBody Note note){
        noteMapper.insertSelective(note);
    }

    @PutMapping
    public void update(@RequestBody Note note){
        noteMapper.updateByPrimaryKeySelective(note);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        noteMapper.deleteByPrimaryKey(id);
    }

    @GetMapping("getNotesLimit")
    public List<FindIdAndTitleAndDescriptionAndCreateTimeByIsDelResult> get(){
        Note note=new Note();
        note.setIsDel(0);
        return noteMapper.findIdAndTitleAndDescriptionAndCreateTimeByIsDel(note);
    }
}
