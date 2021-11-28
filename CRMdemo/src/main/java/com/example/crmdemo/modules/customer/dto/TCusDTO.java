package com.example.crmdemo.modules.customer.dto;

import com.example.crmdemo.modules.customer.model.TCus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ᛟ
 * @date 2021/11/24 - 20:05
 */
@Data
public class TCusDTO extends TCus {

    @ApiModelProperty(value = "当前页")
    private Integer current;

    @ApiModelProperty(value = "每页显示数目")
    private Integer pageSize;
}
