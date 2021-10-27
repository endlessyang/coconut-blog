package com.coconut.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User Object", description="")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "User ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "Username")
    private String username;

    @ApiModelProperty(value = "Password")
    private String password;

    @ApiModelProperty(value = "Email")
    private String email;

    @ApiModelProperty(value = "Create Time")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "Update Time")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
