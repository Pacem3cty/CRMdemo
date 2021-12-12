package com.example.crmdemo.modules.stats.dto;

import com.example.crmdemo.modules.stats.model.CusCTRB;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CusCTRBDto extends CusCTRB {
    @ApiModelProperty(value = "当前页")
    private Integer current;

    @ApiModelProperty(value = "每页显示数目")
    private Integer pageSize;
}
