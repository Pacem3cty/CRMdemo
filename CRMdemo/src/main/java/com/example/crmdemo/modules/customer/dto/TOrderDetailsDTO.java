package com.example.crmdemo.modules.customer.dto;

import com.example.crmdemo.modules.customer.model.TOrderDetails;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TOrderDetailsDTO extends TOrderDetails {
    @ApiModelProperty(value = "当前页")
    private Integer current;

    @ApiModelProperty(value = "每页显示数目")
    private Integer pageSize;
}
