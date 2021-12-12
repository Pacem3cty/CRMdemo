package com.example.crmdemo.modules.stats.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crmdemo.modules.stats.dto.CusCTRBDto;
import com.example.crmdemo.modules.stats.model.CusCTRB;
import com.example.crmdemo.modules.stats.model.EchartsPiePojo;

import java.util.List;


public interface CusCTRBService extends IService<CusCTRB> {

//    List<CusCTRB> findCusCTRB(Page page, Wrapper<CusCTRB> queryWrapper);

    List<CusCTRB> findCusCTRB(Page page, CusCTRBDto cusCTRBDto);

    List<EchartsPiePojo> getOrderStats();//联表查询各客户有效订单量统计

    List<EchartsPiePojo> getTotalStats();//联表查询各客户订单总额统计

    List<EchartsPiePojo> getAovStats();//联表查询各客户平均订单价值统计

    List<EchartsPiePojo> getConsistStats();
}
