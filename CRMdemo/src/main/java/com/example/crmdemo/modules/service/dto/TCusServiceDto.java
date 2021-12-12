package com.example.crmdemo.modules.service.dto;

import com.example.crmdemo.modules.service.model.TCusService;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ᛟ
 * @date 2021/12/4 - 0:29
 */
@Data
public class TCusServiceDto extends TCusService {

    @ApiModelProperty(value = "当前页")
    private Integer current;

    @ApiModelProperty(value = "每页显示数目")
    private Integer pageSize;
}
