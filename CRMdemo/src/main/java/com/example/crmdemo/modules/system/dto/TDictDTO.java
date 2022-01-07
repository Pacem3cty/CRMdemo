package com.example.crmdemo.modules.system.dto;

import com.example.crmdemo.modules.system.model.TDict;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TDictDTO extends TDict {
    @ApiModelProperty(value = "当前页")
    private Integer current;

    @ApiModelProperty(value = "每页显示数目")
    private Integer pageSize;
}
