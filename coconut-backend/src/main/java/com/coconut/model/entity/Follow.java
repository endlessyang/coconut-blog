package com.coconut.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Follow Object", description="")
@AllArgsConstructor
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Parent ID")
    private Integer parentId;

    @ApiModelProperty(value = "Follower ID")
    private Integer followerId;

}
