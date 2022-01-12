package com.example.crmdemo.modules.stats.mapper;

import com.example.crmdemo.modules.stats.model.EchartsPiePojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServiceCTRBMapper {

    List<EchartsPiePojo> queryCsrStats();//联表查询客户满意度统计

    List<EchartsPiePojo> queryServiceTypeStats();//联表查询服务类型统计
}
