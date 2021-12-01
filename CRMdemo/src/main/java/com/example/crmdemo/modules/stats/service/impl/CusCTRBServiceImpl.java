package com.example.crmdemo.modules.stats.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.crmdemo.modules.stats.mapper.CusCTRBMapper;
import com.example.crmdemo.modules.stats.model.CusCTRB;
import com.example.crmdemo.modules.stats.service.CusCTRBService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class CusCTRBServiceImpl extends ServiceImpl<CusCTRBMapper, CusCTRB> implements CusCTRBService {

    @Resource
    CusCTRBMapper cusCTRBMapper;

    @Override
    public List<CusCTRB> findCusCTRB(Page page, Wrapper<CusCTRB> queryWrapper) {
        List<CusCTRB> result =  cusCTRBMapper.pageListCusCTRB(page,new QueryWrapper<CusCTRB>());

        return result;
    }
}
