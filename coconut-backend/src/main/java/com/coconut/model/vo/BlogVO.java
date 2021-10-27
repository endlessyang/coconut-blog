package com.coconut.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class BlogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer blogId;

    private String title;

    private String content;

    private Integer userId;

    private String username;

    private Integer view;

    private List<String> tagNames;

    private Date createTime;

}
