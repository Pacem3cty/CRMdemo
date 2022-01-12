package com.example.crmdemo.modules.stats.service;

import com.example.crmdemo.modules.stats.model.EchartsPiePojo;

import java.util.List;

public interface ServiceCTRBService {

    List<EchartsPiePojo> getServiceTypeStats();//查询服务类型统计

    List<EchartsPiePojo> getCsrStats();
}
