package com.example.crmdemo.modules.stats.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crmdemo.modules.stats.model.CusCTRB;

import java.util.List;


public interface CusCTRBService extends IService<CusCTRB> {

    List<CusCTRB> findCusCTRB(Page page, Wrapper<CusCTRB> queryWrapper);
}
