package com.example.crmdemo.modules.customer.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.math.BigDecimal;
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
 * @since 2021-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_cus_order")
@ApiModel(value="TCusOrder对象", description="")
public class TCusOrder implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "订单编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "客户编号")
    private Integer cusId;

    @ApiModelProperty(value = "订单编号")
    private String orderNum;

    @ApiModelProperty(value = "下单时间")
    private Date orderDate;

    @ApiModelProperty(value = "订单总额")
    private BigDecimal orderTotal;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "支付状态 0 未支付 1 已支付")
    private Integer state;

    @ApiModelProperty(value = "有效状态")
    private Integer isValid;

    @ApiModelProperty(value = "创建日期")
    private Date createDate;

    @ApiModelProperty(value = "修改日期")
    private Date updateDate;


}
