package com.example.crmdemo.modules.customer.model;

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
 * @since 2021-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_cus_contact")
@ApiModel(value="TCusContact对象", description="")
public class TCusContact implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "客户交往记录编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "客户编号")
    private Integer cusId;

    @ApiModelProperty(value = "交往时间")
    private Date contactTime;

    @ApiModelProperty(value = "交往地址")
    private String contactAddress;

    @ApiModelProperty(value = "交往概述")
    private String overview;

    @ApiModelProperty(value = "创建日期")
    private Date createDate;

    @ApiModelProperty(value = "修改日期")
    private Date updateDate;

    @ApiModelProperty(value = "有效状态 0 有效 1 无效")
    private Integer isValid;


}
