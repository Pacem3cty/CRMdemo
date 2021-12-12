package com.example.crmdemo.modules.customer.dto;

import com.example.crmdemo.modules.customer.model.TCusReprieve;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ᛟ
 * @date 2021/12/5 - 20:23
 */
@Data
public class TCusReprieveDTO extends TCusReprieve {

    @ApiModelProperty(value = "当前页")
    private Integer current;

    @ApiModelProperty(value = "每页显示数目")
    private Integer pageSize;
}
