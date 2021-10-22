package com.example.crmdemo.modules.sales.model;

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
 * @since 2021-10-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_cus_dev_plan")
@ApiModel(value="TCusDevPlan对象", description="")
public class TCusDevPlan implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    private Integer saleChanceId;

    private String planItem;

    private Date planDate;

    @ApiModelProperty(value = "执行效果")
    private String exeAffect;

    private Date createDate;

    private Date updateDate;

    private Integer isValid;


}
