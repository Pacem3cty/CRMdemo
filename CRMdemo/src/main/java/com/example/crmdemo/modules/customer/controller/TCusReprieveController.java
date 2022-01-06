package com.example.crmdemo.modules.customer.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.annoation.RequiredPermission;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.customer.dto.TCusReprieveDTO;
import com.example.crmdemo.modules.customer.model.TCusReprieve;
import com.example.crmdemo.modules.customer.model.TOrderDetails;
import com.example.crmdemo.modules.customer.service.TCusReprieveService;
import com.example.crmdemo.modules.service.model.TCusService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wr
 * @since 2021-11-24
 */
@RestController
@RequestMapping("/cusReprieve")
public class TCusReprieveController {

    TCusReprieveService tCusReprieveService;

    @Autowired
    public  void settCusReprieveService(TCusReprieveService tCusReprieveService){
        this.tCusReprieveService = tCusReprieveService;
    }

    @RequiredPermission(code = "0202R1")
    @ApiOperation(value = "查询客户暂缓流失信息")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll(@RequestBody TCusReprieveDTO tCusReprieveDTO) {
        QueryWrapper<TCusReprieve> queryWrapper = new QueryWrapper<>();

        //客户订单编号
        if (tCusReprieveDTO.getLossId() != null && !tCusReprieveDTO.getLossId().equals("")) {
            queryWrapper.eq("loss_id", tCusReprieveDTO.getLossId());
        }

        queryWrapper.eq("is_valid", 0); //查询没有被软删除的记录
        queryWrapper.orderByAsc("id");//按升序排序

        Page page = new Page();//分页查询page类
        page.setCurrent(tCusReprieveDTO.getCurrent());//获取当前页
        page.setSize(tCusReprieveDTO.getPageSize());//获取每页显示条目
        IPage<TOrderDetails> pageResult = tCusReprieveService.findAll(page, queryWrapper);
        return CommonResult.success(pageResult);
    }

    @RequiredPermission(code = "0202R2")
    @ApiOperation(value = "新增暂缓流失详情信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> add(@RequestBody TCusReprieve tCusReprieve) {
        return CommonResult.success(tCusReprieveService.add(tCusReprieve));
    }

    @RequiredPermission(code = "0202R3")
    @ApiOperation(value = "修改暂缓流失详情信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@RequestBody TCusReprieve tCusReprieve) {
        return CommonResult.success(tCusReprieveService.update(tCusReprieve));
    }

    @RequiredPermission(code = "0202R4")
    @ApiOperation(value = "软删除暂缓流失详情信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@RequestBody String ids) {
        JSONObject object = JSONObject.parseObject(ids);
        ids = object.get("ids").toString();
        return CommonResult.success(tCusReprieveService.updateById(ids));
    }

    @RequiredPermission(code = "0202R2")
    @ApiOperation(value = "获取新增暂缓流失详情序号")
    @RequestMapping(value = "/getCurrentId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCurrentId() {
        Integer currentId;

        QueryWrapper<TCusReprieve> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MAX(id) as id");

        List<TCusReprieve> tCusReprieveList = tCusReprieveService.findAll(queryWrapper);
        if (tCusReprieveList.get(0) == null) {
            currentId = 0;
        }
        else {
            currentId = tCusReprieveList.get(0).getId();
        }
        currentId++;
        return CommonResult.success(currentId);
    }

}

