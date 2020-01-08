package com.example.demo.feign;


import com.example.demo.Rq.FileDeleteRq;
import com.example.demo.config.FeignConfiguration;
import com.example.demo.dao.FindIdAndNameAndHeadByIdAndIsDelResult;
import com.example.demo.dao.FindIdAndTitleAndDescriptionAndCreateTimeByIsDelResult;
import com.example.demo.domain.Note;
import com.example.demo.domain.Resume;
import com.example.demo.results.FindIdAndNameByNameAndPasswordAndIsDelResult;
import com.example.demo.domain.User;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import feign.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.*;
import java.util.List;

//@FeignClient(name = "microservice-simple-provider-user")
//public interface UserFeignClient {
//    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
//    public User findById(@PathVariable("id")Long id);
//}
@FeignClient(name = "microservice-info",
        configuration = FeignConfiguration.class)
public  interface IFeignClient {

    //USER系列
    @RequestLine("POST /user")
    public void userAdd(@RequestBody User user);

    @RequestLine("PUT /user")
    public void userUpdate(@RequestBody User user);

    @RequestLine("GET /user/{id}")
    public User userGet(@Param("id") String id);

    @RequestLine("GET /user/restricted/{id}")
    public FindIdAndNameAndHeadByIdAndIsDelResult userGetInfo(@Param("id") String id);

    @RequestLine("DELETE /user/{id}")
    public void userDelete(@Param("id") String id);

    @RequestLine("POST /note")
    public void noteAdd(@RequestBody Note note);

    @RequestLine("PUT /note")
    public void noteUpdate(@RequestBody Note note);

    @RequestLine("GET /note/{id}")
    public Note noteGet(@Param("id") String id);

    @RequestLine("DELETE /note/{id}")
    public void noteDelete(@Param("id") String id);


    @RequestLine("POST /resume")
    public void resumeAdd(@RequestBody Resume resume);

    @RequestLine("PUT /resume")
    public void resumeUpdate(@RequestBody Resume resume);

    @RequestLine("GET /resume/{id}")
    public Resume resumeGet(@Param("id") String id);

    @RequestLine("GET /resume/my")
    public Resume resumeMine();

    @RequestLine("DELETE /resume/{id}")
    public void resumeDelete(@Param("id") String id);

    @RequestLine("POST/user/login")
    public FindIdAndNameByNameAndPasswordAndIsDelResult find1(@RequestBody User user);

    //FILEDELETE
    @RequestLine("POST /file/delete")
    public void fileDelete(@RequestBody FileDeleteRq fileDeleteRq);

    @RequestLine("GET/note/getNotesLimit")
    public List<FindIdAndTitleAndDescriptionAndCreateTimeByIsDelResult> findNotesLimit();
}

