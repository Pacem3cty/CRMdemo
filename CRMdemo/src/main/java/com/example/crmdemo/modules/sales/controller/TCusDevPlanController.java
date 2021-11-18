package com.example.crmdemo.modules.sales.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.sales.dto.TCusDevPlanDto;
import com.example.crmdemo.modules.sales.model.TCusDevPlan;
import com.example.crmdemo.modules.sales.model.TSaleChance;
import com.example.crmdemo.modules.sales.service.TCusDevPlanService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wr
 * @since 2021-10-20
 */
@RestController
@RequestMapping("/sales/tCusDevPlan")
public class TCusDevPlanController {


    TCusDevPlanService tCusDevPlanService;

    @Autowired
    public void settCusDevPlanService(TCusDevPlanService tCusDevPlanService) {
        this.tCusDevPlanService = tCusDevPlanService;
    }

    @ApiOperation(value = "获取新增营销机会编号")
    @RequestMapping(value = "/getCurrentId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCurrentId() {
        Integer currentId;
        QueryWrapper<TCusDevPlan> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MAX(id) as id");
        List<TCusDevPlan> tCusDevPlanList = tCusDevPlanService.findAll(queryWrapper);
        if (tCusDevPlanList.get(0) == null) {
            currentId = 0;
        }
        else {
            currentId = tCusDevPlanList.get(0).getId();
        }
        currentId++;
        return CommonResult.success(currentId);
    }

    @ApiOperation(value = "查询计划项信息")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll(@RequestBody TCusDevPlanDto tCusDevPlanDto) {
        QueryWrapper<TCusDevPlan> queryWrapper = new QueryWrapper<>();

        //查询营销机会编号
        if (tCusDevPlanDto.getSaleChanceId() != null && !tCusDevPlanDto.getSaleChanceId().equals("")) {
            queryWrapper.eq("sale_chance_id", tCusDevPlanDto.getSaleChanceId());

        }
        queryWrapper.eq("is_valid", 0); //查询没有被软删除的记录
        queryWrapper.orderByAsc("id");//按升序排序

        Page page = new Page();//分页查询page类
        page.setCurrent(tCusDevPlanDto.getCurrent());//获取当前页数
        page.setSize(tCusDevPlanDto.getPageSize());//获取每页显示条目
        IPage<TCusDevPlan> pageResult = tCusDevPlanService.findAll(page, queryWrapper);
        return CommonResult.success(pageResult);
    }

    @ApiOperation(value = "新增营销机会信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> add(@RequestBody TCusDevPlan tCusDevPlan) {
        return CommonResult.success(tCusDevPlanService.add(tCusDevPlan));
    }

    @ApiOperation(value = "修改营销机会信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@RequestBody TCusDevPlan tCusDevPlan) {
        return CommonResult.success(tCusDevPlanService.update(tCusDevPlan));
    }

    @ApiOperation(value = "软删除营销机会信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@RequestBody String ids) {
        JSONObject object = JSONObject.parseObject(ids);
        ids = object.get("ids").toString();
        return CommonResult.success(tCusDevPlanService.updateById(ids));
    }
}

