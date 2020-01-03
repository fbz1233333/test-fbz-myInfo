package com.example.demo.controllerV;

import com.example.demo.Rq.V2LoginRq;
import com.example.demo.Rq.V2LogoutRq;
import com.example.demo.Rq.V2RegisterRq;
import com.example.demo.domain.User;
import com.example.demo.feign.IFeignClient;
import com.example.demo.results.FindIdAndNameByNameAndPasswordAndIsDelResult;
import com.example.demo.utils.RedisUtil;
import com.example.demo.utils.TokenUtils;
import com.netflix.client.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2")
public class v2Controller {

    static Logger logger = LoggerFactory.getLogger(v2Controller.class);

    @Autowired
    IFeignClient iFeignClient;

    @PostMapping("register")
    public void v2add(@RequestBody @Validated V2RegisterRq v2Rq){
        logger.info("请求所得v2rq:{}",v2Rq);
        v2Rq.setId(UUID.randomUUID().toString());

        User user=new User();
        BeanUtils.copyProperties(v2Rq,user);
        logger.info("转化后的user:{}",user);
        iFeignClient.userAdd(user);
    }

    @Value("${baseInfo.normal}")
    Integer is_normal;


    @PostMapping("login")
    public HashMap<String, Object> v2Login(@RequestBody @Validated V2LoginRq v2Rq, Model model){
        logger.info("请求所得v2rq:{}",v2Rq);
        User user=new User();
        BeanUtils.copyProperties(v2Rq,user);
        user.setIsDel(is_normal);
        logger.info("转化所得USER:{}",user);
        FindIdAndNameByNameAndPasswordAndIsDelResult result=iFeignClient.find1(user);
        logger.info("微服务请求所得:{}",result);
        HashMap<String ,Object> map=new HashMap<>();

        if (result==null){
            map.put("loginResult","NO_SUCH_USER");
        }else {
            map.put("loginResult","LOGIN_SUCCESS") ;
            map.put("userInfo",result);
            String token=tokenUtils.getToken(result.getId(),result.getName());
            logger.info("生成的TOKEN:{}",token);
            redisUtil.set(result.getId(),token);

//            logger.info("常态model为:{}",model);
//            model.addAttribute("testData2","这是第二条data"+result.getName());
//            logger.info("常态model为:{}",model);

            map.put("token",token);

        }
        return map;
    }

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    RedisUtil redisUtil;

    @PostMapping("logout")
    public HashMap<String,Object> logout(@RequestBody @Validated V2LogoutRq v2LogoutRq){

        String realId=v2LogoutRq.getId();
//        logger.info("length:{}",v2LogoutRq.getId().length());
        logger.info("要退出的用户ID:{}",realId);

        redisUtil.delete(realId);

        HashMap<String,Object> map=new HashMap<>();
        map.put("logoutResult","LOGOUT_SUCCESS");
        return map;
    }
}
