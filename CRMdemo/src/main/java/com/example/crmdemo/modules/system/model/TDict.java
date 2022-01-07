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
 * @since 2022-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_dict")
@ApiModel(value="TDict对象", description="")
public class TDict implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "数据字典主键id")
    private Integer id;

    @ApiModelProperty(value = "数据类型")
    private String dataType;

    @ApiModelProperty(value = "数据名称")
    private String dataName;

    @ApiModelProperty(value = "数据值")
    private String dataValue;

    @ApiModelProperty(value = "有效状态 0 有效 | 1 无效")
    private Integer isValid;

    @ApiModelProperty(value = "创建日期")
    private Date createDate;

    @ApiModelProperty(value = "修改日期")
    private Date updateDate;


}
