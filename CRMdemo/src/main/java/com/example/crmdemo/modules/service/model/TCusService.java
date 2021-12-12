package com.example.crmdemo.modules.service.model;

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
 * @since 2021-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_cus_service")
@ApiModel(value="TCusService对象", description="")
public class TCusService implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "客户服务编号")
    private Integer id;

    @ApiModelProperty(value = "服务类型")
    private String serviceType;

    @ApiModelProperty(value = "概述")
    private String overview;

    @ApiModelProperty(value = "客户名称")
    private String customer;

    @ApiModelProperty(value = "服务状态 0 分配状态 | 1 处理状态 | 2 反馈状态 | 3 归档状态")
    private Integer state;

    @ApiModelProperty(value = "服务请求")
    private String serviceRequest;

    @ApiModelProperty(value = "创建人")
    private String createPerson;

    @ApiModelProperty(value = "指派人")
    private String assignPerson;

    @ApiModelProperty(value = "分配日期")
    private Date assignDate;

    @ApiModelProperty(value = "服务处理")
    private String serviceProcess;

    @ApiModelProperty(value = "处理人")
    private String processPerson;

    @ApiModelProperty(value = "处理时间")
    private Date processDate;

    @ApiModelProperty(value = "处理结果")
    private String processResult;

    @ApiModelProperty(value = "客户满意度")
    private String csr;

    @ApiModelProperty(value = "有效状态")
    private Integer isValid;

    @ApiModelProperty(value = "创建日期")
    private Date createDate;

    @ApiModelProperty(value = "修改日期")
    private Date updateDate;


}
