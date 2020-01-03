package com.example.demo.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;

public class FeignConfiguration {
    @Bean
    public Contract feignContract(){
        return new Contract.Default();
    }
}
