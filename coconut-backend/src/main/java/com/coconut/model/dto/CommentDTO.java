package com.coconut.model.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "The content can't be blank")
    private String content;

    @NotNull(message = "The blog ID can't be blank")
    private Integer blogId;

}
