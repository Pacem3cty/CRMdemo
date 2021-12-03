package com.example.crmdemo.modules.stats.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CusCTRBDTO {
    @ApiModelProperty(value = "当前页")
    private Integer current;

    @ApiModelProperty(value = "每页显示数目")
    private Integer pageSize;
}
