package com.example.crmdemo.modules.sales.dto;

import com.example.crmdemo.modules.sales.model.TCusDevPlan;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TCusDevPlanDto extends TCusDevPlan {
    @ApiModelProperty(value = "当前页")
    private Integer current;

    @ApiModelProperty(value = "每页显示数目")
    private Integer pageSize;

}