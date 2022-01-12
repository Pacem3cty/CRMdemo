package com.example.crmdemo.modules.stats.service.impl;

import com.example.crmdemo.modules.stats.mapper.ServiceCTRBMapper;
import com.example.crmdemo.modules.stats.model.EchartsPiePojo;
import com.example.crmdemo.modules.stats.service.ServiceCTRBService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ServiceCTRBServiceImpl implements ServiceCTRBService {

    @Resource
    ServiceCTRBMapper serviceCTRBMapper;

    @Override
    public List<EchartsPiePojo> getServiceTypeStats() {
        return serviceCTRBMapper.queryServiceTypeStats();
    }

    @Override
    public List<EchartsPiePojo> getCsrStats() {return serviceCTRBMapper.queryCsrStats();}
}
