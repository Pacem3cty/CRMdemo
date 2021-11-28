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
@TableName("t_cus")
@ApiModel(value="TCus对象", description="")
public class TCus implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "客户编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "客户编号")
    private String cusNum;

    @ApiModelProperty(value = "客户名称")
    private String cusName;

    @ApiModelProperty(value = "所属地区")
    private String area;

    @ApiModelProperty(value = "客户经理")
    private String cusManager;

    @ApiModelProperty(value = "客户级别")
    private String level;

    @ApiModelProperty(value = "客户满意度")
    private String csr;

    @ApiModelProperty(value = "客户信用度")
    private String ccr;

    @ApiModelProperty(value = "客户地址")
    private String cusAddress;

    @ApiModelProperty(value = "邮编")
    private String postalCode;

    @ApiModelProperty(value = "客户电话")
    private String cusPhone;

    @ApiModelProperty(value = "传真")
    private String cusFax;

    @ApiModelProperty(value = "客户网站")
    private String website;

    @ApiModelProperty(value = "统一社会信用代码")
    private String socialCreditCode;

    @ApiModelProperty(value = "法人代表")
    private String artificialPerson;

    @ApiModelProperty(value = "注册资金")
    private String registeredCapital;

    @ApiModelProperty(value = "年营业额")
    private String annualTurnover;

    @ApiModelProperty(value = "开户银行")
    private String accountBank;

    @ApiModelProperty(value = "银行账号")
    private String accountNum;

    @ApiModelProperty(value = "流失状态 0 未流失 1 暂缓流失 2 流失")
    private Integer lossStatus;

    @ApiModelProperty(value = "有效状态 0 有效 1 无效")
    private Integer isValid;

    @ApiModelProperty(value = "创建日期")
    private Date createDate;

    @ApiModelProperty(value = "修改日期")
    private Date updateDate;


}
