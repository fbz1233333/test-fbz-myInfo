package com.example.demo.dao;
import com.example.demo.results.FindIdAndNameByNameAndPasswordAndIsDelResult;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


FindIdAndNameByNameAndPasswordAndIsDelResult findIdAndNameByNameAndPasswordAndIsDel(User user);


}
