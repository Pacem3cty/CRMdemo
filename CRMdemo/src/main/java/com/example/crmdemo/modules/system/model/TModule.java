package com.example.crmdemo.modules.system.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

import com.example.crmdemo.util.Tree;
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
@TableName("t_module")
@ApiModel(value="TModule对象", description="")
public class TModule implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源编号")
    private Integer id;

    @ApiModelProperty(value = "资源名称")
    private String moduleName;

    @ApiModelProperty(value = "资源样式")
    private String moduleStyle;

    @ApiModelProperty(value = "url地址")
    private String url;

    @ApiModelProperty(value = "上级资源编号")
    private Integer parentId;

    @ApiModelProperty(value = "上级资源权限码")
    private String parentOptValue;

    @ApiModelProperty(value = "层级")
    private Integer grade;

    @ApiModelProperty(value = "权限码")
    private String optValue;

    @ApiModelProperty(value = "序号（靠前程度）")
    private Integer orders;

    @ApiModelProperty(value = "是否删除  0 未删除  1 已删除")
    private Integer isValid;

    @ApiModelProperty(value = "建档时间")
    private Date createDate;

    @ApiModelProperty(value = "修改时间")
    private Date updateDate;

}
