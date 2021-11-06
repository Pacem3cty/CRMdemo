package com.example.crmdemo.modules.system.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="ZTree对象", description="")
public class ZTree {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "子节点编号")
    private Integer id;

    @ApiModelProperty(value = "父节点编号")
    private String pid;

    @ApiModelProperty(value = "节点名称")
    private String name;
}
