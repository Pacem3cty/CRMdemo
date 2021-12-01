package com.example.crmdemo.modules.system.dto;

import com.example.crmdemo.modules.system.model.TUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TUserDTO extends TUser {
    @ApiModelProperty(value = "当前记录索引值")
    private Integer current;

    @ApiModelProperty(value = "每页显示数目")
    private Integer pageSize;
}

