package com.coconut.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Blog Object", description="")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Blog ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "Blog Title")
    private String title;

    @ApiModelProperty(value = "Blog Content")
    private String content;

    @ApiModelProperty(value = "Blogger ID")
    private Integer userId;

    @ApiModelProperty(value = "Page View")
    private Integer view = 0;

    @ApiModelProperty(value = "Create Time")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "Update Time")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
