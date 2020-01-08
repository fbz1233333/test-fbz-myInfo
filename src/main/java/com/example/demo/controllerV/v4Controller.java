package com.example.demo.controllerV;

import com.example.demo.annotiation.UserIsAuthor;
import com.example.demo.annotiation.UserLoginToken;
import com.example.demo.domain.Note;
import com.example.demo.dto.NoteDto1;
import com.example.demo.dto.NoteDto2;
import com.example.demo.dto.NoteDto3;
import com.example.demo.feign.IFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("/api/v4")
@CrossOrigin
public class v4Controller {

    static Logger logger = LoggerFactory.getLogger(v4Controller.class);

    @Autowired
    IFeignClient iFeignClient;

    @GetMapping("getNotes_limit")
    public ResponseEntity<java.util.List<com.example.demo.dao.FindIdAndTitleAndDescriptionAndCreateTimeByIsDelResult>> get(){
        return new ResponseEntity<>(iFeignClient.findNotesLimit(), HttpStatus.OK);
    }

    @PostMapping("getOneNote")
    public Note get(@RequestBody @Validated NoteDto3 noteDto3){
        return iFeignClient.noteGet(noteDto3.getId());
    }


    @PostMapping("addNote")
    @UserLoginToken
    public void add(@RequestBody @Validated NoteDto1 noteDto1){
        logger.info("获取请求为:{}",noteDto1);
        Note note=new Note();

        BeanUtils.copyProperties(noteDto1,note);
        note.setCreateBy(request.getHeader(loginUserId));
        note.setId(UUID.randomUUID().toString());
        iFeignClient.noteAdd(note);
    }

    @Value("${headerInfo.loginUserId.name}")
    String loginUserId;

    @Autowired
    HttpServletRequest request;

    @PostMapping("update")
    @UserLoginToken
    @UserIsAuthor
    public void update(@RequestBody @Validated NoteDto2 noteDto2){
        Note note=new Note();

        note.setId(request.getHeader(noteIdTag));

        BeanUtils.copyProperties(noteDto2,note);
        iFeignClient.noteUpdate(note);
    }
    @Value("${headerInfo.updateNoteId.name}")
    String noteIdTag;


    @PostMapping("delete")
    @UserLoginToken
    @UserIsAuthor
    public void delete(@RequestBody @Validated NoteDto3 noteDto3){


        noteDto3.setId(request.getHeader(noteIdTag));

        logger.info("HEADERS中获取的USER_ID为:{}",request.getHeader("LOGIN_USER_ID"));
        logger.info("RequestBody中获取的NOTE_ID为:{}",noteDto3.getId());

        //....

        iFeignClient.noteDelete(noteDto3.getId());
    }

}
