package com.example.crmdemo.modules.system.model;

import com.example.crmdemo.util.TreeControl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
/**
 * 树型数据接收类
 *
 */
@Data
@ApiModel(value="与Element-UI框架适配的树控件对象", description="")
public class TreeControlPojo implements TreeControl<TreeControlPojo> {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "子节点编号")
    private String id;

    @ApiModelProperty(value = "父节点编号")
    private String pid;

    @ApiModelProperty(value = "节点名称")
    private String label;

    @ApiModelProperty(value = "子节点链表")
    private List<TreeControlPojo> childList;

}
