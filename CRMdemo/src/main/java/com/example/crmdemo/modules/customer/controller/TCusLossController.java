package com.example.crmdemo.modules.customer.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.api.CommonResult;

import com.example.crmdemo.modules.customer.dto.TCusLossDTO;
import com.example.crmdemo.modules.customer.model.TCus;
import com.example.crmdemo.modules.customer.model.TCusLoss;

import com.example.crmdemo.modules.customer.service.TCusLossService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wr
 * @since 2021-11-24
 */
@RestController
@RequestMapping("/cusLoss")
public class TCusLossController {

    TCusLossService tCusLossService;

    @Autowired
    public void settCusLossService(TCusLossService tCusLossService){
        this.tCusLossService = tCusLossService;
    }

    @ApiOperation(value = "查询客户流失信息")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll(@RequestBody TCusLossDTO tCusLossDTO) {
        QueryWrapper<TCusLoss> queryWrapper = new QueryWrapper<>();

        //客户名称
        if (tCusLossDTO.getCusName() != null && !tCusLossDTO.getCusName().equals("")) {
            queryWrapper.like("cus_name", tCusLossDTO.getCusName());
        }

        //客户经理
        if (tCusLossDTO.getCusManager() != null && !tCusLossDTO.getCusManager().equals("")) {
            queryWrapper.eq("cus_manager", tCusLossDTO.getCusManager());
        }


        queryWrapper.eq("is_valid", 0); //查询没有被软删除的记录
        queryWrapper.orderByAsc("id");//按升序排序

        Page page = new Page();//分页查询page类
        page.setCurrent(tCusLossDTO.getCurrent());//获取当前记录索引值
        page.setSize(tCusLossDTO.getPageSize());//获取每页显示条目
        IPage<TCus> pageResult = tCusLossService.findAll(page, queryWrapper);
        return CommonResult.success(pageResult);
    }

    @ApiOperation(value = "查询客户名称信息")
    @RequestMapping(value = "/listCusName", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult listCusName() {
        QueryWrapper<TCusLoss> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("cus_name as cusName").eq("is_valid", 0).groupBy("cus_name");//GROUP BY 较 DISTINCT 性能更优
        List<Map<String, Object>> tCusLossList = tCusLossService.query(queryWrapper);
        return CommonResult.success(tCusLossList);
    }

    @ApiOperation(value = "查询客户名称信息")
    @RequestMapping(value = "/listCusManager", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult listCusManager() {
        QueryWrapper<TCusLoss> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("cus_manager as cusManager").eq("is_valid", 0).groupBy("cus_manager");//GROUP BY 较 DISTINCT 性能更优
        List<Map<String, Object>> tCusLossList = tCusLossService.query(queryWrapper);
        return CommonResult.success(tCusLossList);
    }
}

