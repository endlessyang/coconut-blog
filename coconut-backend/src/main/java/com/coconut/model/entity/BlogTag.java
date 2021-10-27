package com.coconut.model.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BlogTag Object", description="")
@Builder
public class BlogTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Blog ID")
    private Integer blogId;

    @ApiModelProperty(value = "Tag ID")
    private Integer tagId;

}
