package com.example.crmdemo.modules.sales.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-10-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_sale_chance")
@ApiModel(value="TSaleChance对象", description="")
public class TSaleChance implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "机会来源")
    private String chanceSource;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "成功概率")
    private Integer probability;

    @ApiModelProperty(value = "概要")
    private String overview;

    @ApiModelProperty(value = "联系人")
    private String contractPerson;

    @ApiModelProperty(value = "修改日期")
    private String contractPhone;

    @ApiModelProperty(value = "描述")
    private String description;


    @ApiModelProperty(value = "创建人")
    private String createPerson;


    @ApiModelProperty(value = "创建日期")
    private Date createDate;

    @ApiModelProperty(value = "分配人")
    private String assignPerson;

    @ApiModelProperty(value = "分配时间")
    private Date assignDate;

    @ApiModelProperty(value = "分配状态 0 未分配 1 已分配")
    private Integer state;

    @ApiModelProperty(value = "开发结果 0 开发中 1 开发成功 2 开发失败")
    private Integer devResult;

    @ApiModelProperty(value = "有效状态 0 有效 1 无效")
    private Integer isValid;

    @ApiModelProperty(value = "修改日期")
    private Date updateDate;


}
