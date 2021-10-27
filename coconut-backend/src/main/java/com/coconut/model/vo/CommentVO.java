package com.coconut.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class CommentVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String content;

    private String username;

    private Date createTime;

}
