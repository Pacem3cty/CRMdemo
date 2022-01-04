package com.example.crmdemo.modules.system.model;

import com.example.crmdemo.utils.TreeTable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TreeTablePojo implements TreeTable<TreeTablePojo> {
    @ApiModelProperty(value = "资源编号")
    private String id;

    @ApiModelProperty(value = "上级资源编号")
    private String parentId;

    @ApiModelProperty(value = "资源名称")
    private String moduleName;

    @ApiModelProperty(value = "url地址")
    private String url;

    @ApiModelProperty(value = "权限码")
    private String optValue;


    @ApiModelProperty(value = "子节点链表")
    private List<TreeTablePojo> childList;
}
