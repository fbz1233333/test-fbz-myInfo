package com.example.demo.dao;
import org.apache.ibatis.annotations.Param;
import com.example.demo.dao.FindIdAndCreateByResult;

import com.example.demo.domain.Note;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoteMapper {
    int deleteByPrimaryKey(String id);

    int insert(Note record);

    int insertSelective(Note record);

    Note selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Note record);

    int updateByPrimaryKey(Note record);

    List<FindIdAndTitleAndDescriptionAndCreateTimeByIsDelResult> findIdAndTitleAndDescriptionAndCreateTimeByIsDel(Note note);


    Note findByIdAndCreateBy(@Param("id")String id,@Param("createBy")String createBy);




}
