package com.example.crmdemo.modules.customer.dto;

import com.example.crmdemo.modules.customer.model.TCusContact;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ᛟ
 * @date 2021/11/28 - 15:59
 */
@Data
public class TCusContactDTO extends TCusContact {
    @ApiModelProperty(value = "当前页")
    private Integer current;

    @ApiModelProperty(value = "每页显示数目")
    private Integer pageSize;
}
