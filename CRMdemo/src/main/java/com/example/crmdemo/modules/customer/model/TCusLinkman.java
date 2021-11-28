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
@TableName("t_cus_linkman")
@ApiModel(value="TCusLinkman对象", description="")
public class TCusLinkman implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "客户联系人编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "客户编号")
    private Integer cusId;

    @ApiModelProperty(value = "联系人名称")
    private String linkmanName;

    @ApiModelProperty(value = "性别 0 男 | 1 女")
    private Integer sex;

    @ApiModelProperty(value = "职位")
    private String position;

    @ApiModelProperty(value = "办公电话")
    private String officePhone;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "有效状态")
    private Integer isValid;

    @ApiModelProperty(value = "创建日期")
    private Date createDate;

    @ApiModelProperty(value = "修改日期")
    private Date updateDate;


}
