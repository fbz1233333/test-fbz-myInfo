package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class TokenUtils {
    public String getToken(String id,String name) {
        String token="";
        token= JWT.create().withAudience(id).withAudience(name).withAudience(new Random().toString())
                .sign(Algorithm.HMAC256(new Date().toString()));
        return token;
    }
}
