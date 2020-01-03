package com.example.demo.Rq;

import com.example.demo.basic.BasicInfo;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Data
public class V1C1Rq extends BasicInfo {
    @NotNull(message = "ID不能为空")
    @Size(min = 36,max =36,message = "ID长度必须为36")
    private String id;

    @NotNull(message = "文章不能为空")
    @Size(min = 1,message = "文章长度至少大于1个字符")
    private String textInfo;

    @NotNull(message = "标题不能为空")
    private String title;

    @NotNull(message = "ID不能为空")
    @Size(min = 36,max =36,message = "作者ID长度必须为36")
    private String authorId;


    @NotNull(message = "NAME不能为空")
    @Size(min = 3,max = 6,message = "NAME不能为空")
    private String authorName;

    @Valid
    private List<String> tag;

}
