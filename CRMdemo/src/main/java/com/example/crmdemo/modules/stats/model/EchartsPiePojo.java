package com.example.crmdemo.modules.stats.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ᛟ
 * @date 2021/12/11 - 17:26
 */

@Data
public class EchartsPiePojo {
    @ApiModelProperty(value = "饼状图标签名称")
    private String name;

    @ApiModelProperty(value = "饼状图数值")
    private BigDecimal value;
}
