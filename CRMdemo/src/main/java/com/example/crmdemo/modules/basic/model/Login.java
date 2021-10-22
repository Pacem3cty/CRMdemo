package com.example.crmdemo.modules.basic.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="登录对象", description="")
public class Login {

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "登录密码")
    private String userPwd;

    @ApiModelProperty(value = "新密码")
    private String newPwd;

}
