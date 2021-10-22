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
@TableName("t_permission")
@ApiModel(value="TPermission对象", description="")
public class TPermission implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    private Integer roleId;

    private Integer moduleId;

    private String aclValue;

    private Date createDate;

    private Date updateDate;


}
