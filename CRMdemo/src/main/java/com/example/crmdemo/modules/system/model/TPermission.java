package com.example.crmdemo.modules.system.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wr
 * @since 2021-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_permission")
@ApiModel(value="TPermission对象", description="")
public class TPermission implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "权限编号")
    private Integer id;

    @ApiModelProperty(value = "角色编号")
    private Integer roleId;

    @ApiModelProperty(value = "资源编号")
    private Integer moduleId;

    @ApiModelProperty(value = "权限码")
    private String aclValue;

    @ApiModelProperty(value = "建档时间")
    private Date createDate;

    @ApiModelProperty(value = "修改时间")
    private Date updateDate;


}
