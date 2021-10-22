package com.example.crmdemo.modules.sales.dto;

import com.example.crmdemo.modules.sales.model.TSaleChance;
import io.swagger.annotations.ApiModelProperty;

public class TSaleChanceDto extends TSaleChance {
    @ApiModelProperty(value = "当前页")
    private Integer current;

    @ApiModelProperty(value = "每页显示数目")
    private Integer pageSize;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
