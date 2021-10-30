package com.example.crmdemo.modules.system.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="封装登录信息对象", description="")
public class LoginInfo {

    private Boolean pwdIsTrue;//密码比对结果

    private Integer id;

    private String userName;

    private String trueName;

    private String token;//根据用户名和用户密码生成的密钥

    private String loginTip;//登录提示信息
}
