package com.example.crmdemo.modules.permission.model;

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
 * @since 2021-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_module")
@ApiModel(value="TModule对象", description="")
public class TModule implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    private String moduleName;

    private String url;

    private Integer parentId;

    private String parentOptValue;

    private Integer grade;

    private String optValue;

    private Integer orders;

    private Integer isValid;

    private Date createDate;

    private Date updateDate;


}
