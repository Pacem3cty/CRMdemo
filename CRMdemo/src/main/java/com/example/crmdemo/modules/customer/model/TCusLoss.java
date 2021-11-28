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
@TableName("t_cus_loss")
@ApiModel(value="TCusLoss对象", description="")
public class TCusLoss implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "客户流失记录编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "客户编号")
    private Integer cusId;

    private String cusName;

    @ApiModelProperty(value = "客户经理")
    private String cusManager;

    @ApiModelProperty(value = "最后下单时间")
    private Date lastOrderTime;

    @ApiModelProperty(value = "确认流失时间")
    private Date confirmLossTime;

    @ApiModelProperty(value = "流失状态")
    private Integer state;

    @ApiModelProperty(value = "流失原因")
    private String lossReason;

    @ApiModelProperty(value = "有效状态")
    private Integer isValid;

    @ApiModelProperty(value = "创建日期")
    private Date createDate;

    @ApiModelProperty(value = "更新日期")
    private Date updateDate;


}
