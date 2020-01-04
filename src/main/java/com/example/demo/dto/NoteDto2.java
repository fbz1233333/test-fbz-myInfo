package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class NoteDto2 {

    @Size(min=36,max = 36,message = "ID is wrong")
    String id;

    @Size(min = 6,message = "at least 6 chars")
    String title;

    @Size(min = 8,message = "at least 8 chars")
    String description;

    @Size(min = 10,message = "at least 10 chars")
    String text;
}
