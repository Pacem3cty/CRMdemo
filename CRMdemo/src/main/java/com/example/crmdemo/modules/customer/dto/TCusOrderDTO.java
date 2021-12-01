package com.example.crmdemo.modules.customer.dto;

import com.example.crmdemo.modules.customer.model.TCusOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TCusOrderDTO extends TCusOrder {

    @ApiModelProperty(value = "当前记录索引值")
    private Integer current;

    @ApiModelProperty(value = "每页显示数目")
    private Integer pageSize;
}
