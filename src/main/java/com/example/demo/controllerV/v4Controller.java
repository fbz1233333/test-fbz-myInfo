package com.example.demo.controllerV;

import com.example.demo.annotiation.UserLoginToken;
import com.example.demo.domain.Note;
import com.example.demo.exception.TokenConfirmException;
import com.example.demo.feign.IFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v4")
public class v4Controller {

    static Logger logger = LoggerFactory.getLogger(v4Controller.class);

    @Autowired
    IFeignClient iFeignClient;

    @GetMapping("getNotes_limit")
    public ResponseEntity get(){
        return new ResponseEntity(iFeignClient.findNotesLimit(), HttpStatus.OK);
    }

    @GetMapping("getOneNote/{id}")
    public Note get(@PathVariable String  id){
        return iFeignClient.noteGet(id);
    }
}
