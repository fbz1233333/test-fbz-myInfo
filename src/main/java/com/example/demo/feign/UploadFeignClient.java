package com.example.demo.feign;

import feign.Param;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "microservice-file",
        configuration = UploadFeignClient.MultiPartSupportConfig.class)
public interface UploadFeignClient {
    //FILE转移系列
    @PostMapping(value = "/api/upload", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String upload(@RequestPart("file") MultipartFile file, @RequestParam("path") String filePath);


    class MultiPartSupportConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }

}
