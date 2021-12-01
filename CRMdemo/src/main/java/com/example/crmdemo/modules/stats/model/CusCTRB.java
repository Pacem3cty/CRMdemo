package com.example.crmdemo.modules.stats.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CusCTRB {

    @ApiModelProperty(value = "客户序号")
    private Integer id;

    @ApiModelProperty(value = "客户序号")
    private String cusNum;

    @ApiModelProperty(value = "客户名称")
    private String cusName;

    @ApiModelProperty(value = "客户电话")
    private String cusPhone;

    @ApiModelProperty(value = "客户经理")
    private String cusManager;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal ctrb;

    @ApiModelProperty(value = "有效订单数")
    private Integer orderCount;

    @ApiModelProperty(value = "平均订单价值")
    private BigDecimal aov;


}
