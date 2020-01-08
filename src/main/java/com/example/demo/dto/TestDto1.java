package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class TestDto1 {

    @Size(min = 36, max = 36, message = "ID is wrong")
    String id;

}
