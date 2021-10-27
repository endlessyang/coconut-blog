package com.coconut.model.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
public class BlogDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer blogId;

    @NotBlank(message = "The title can't be blank")
    private String title;

    @NotBlank(message = "The content can't be blank")
    private String content;

    @NotEmpty(message = "The tag name can't be blank")
    private List<String> tagNames;

}
