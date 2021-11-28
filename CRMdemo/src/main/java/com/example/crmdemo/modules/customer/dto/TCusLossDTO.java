package com.example.crmdemo.modules.customer.dto;

import com.example.crmdemo.modules.customer.model.TCusLoss;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ᛟ
 * @date 2021/11/24 - 23:22
 */
@Data
public class TCusLossDTO extends TCusLoss {

    @ApiModelProperty(value = "当前页")
    private Integer current;

    @ApiModelProperty(value = "每页显示数目")
    private Integer pageSize;
}
