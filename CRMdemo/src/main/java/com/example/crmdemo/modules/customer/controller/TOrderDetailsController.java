package com.example.crmdemo.modules.customer.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.customer.dto.TOrderDetailsDTO;
import com.example.crmdemo.modules.customer.model.TOrderDetails;
import com.example.crmdemo.modules.customer.service.TOrderDetailsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
@RequestMapping("/cusOrderDetails")
public class TOrderDetailsController {

    private TOrderDetailsService tOrderDetailsService;

    @Autowired
    public void settOrderDetailsService(TOrderDetailsService tOrderDetailsService){
        this.tOrderDetailsService = tOrderDetailsService;
    }

    @ApiOperation(value = "查询客户订单详情信息")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll(@RequestBody TOrderDetailsDTO tOrderDetailsDTO) {
        QueryWrapper<TOrderDetails> queryWrapper = new QueryWrapper<>();

        //客户订单编号
        if (tOrderDetailsDTO.getOrderId() != null && !tOrderDetailsDTO.getOrderId().equals("")) {
            queryWrapper.eq("order_id", tOrderDetailsDTO.getOrderId());
        }

        queryWrapper.eq("is_valid", 0); //查询没有被软删除的记录
        queryWrapper.orderByAsc("id");//按升序排序

        Page page = new Page();//分页查询page类
        page.setCurrent(tOrderDetailsDTO.getCurrent());//获取当前页
        page.setSize(tOrderDetailsDTO.getPageSize());//获取每页显示条目
        IPage<TOrderDetails> pageResult = tOrderDetailsService.findAll(page, queryWrapper);
        return CommonResult.success(pageResult);
    }

    @ApiOperation(value = "新增客户订单详情信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> add(@RequestBody TOrderDetails tOrderDetails) {
        return CommonResult.success(tOrderDetailsService.add(tOrderDetails));
    }

    @ApiOperation(value = "修改客户订单详情信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@RequestBody TOrderDetails tOrderDetails) {
        return CommonResult.success(tOrderDetailsService.update(tOrderDetails));
    }

    @ApiOperation(value = "软删除客户订单详情信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@RequestBody String ids) {
        JSONObject object = JSONObject.parseObject(ids);
        ids = object.get("ids").toString();
        return CommonResult.success(tOrderDetailsService.updateById(ids));
    }

    @ApiOperation(value = "获取新增客户订单详情序号")
    @RequestMapping(value = "/getCurrentId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCurrentId() {
        Integer currentId;

        QueryWrapper<TOrderDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MAX(id) as id");

        List<TOrderDetails> tOrderDetailsList = tOrderDetailsService.findAll(queryWrapper);
        if (tOrderDetailsList.get(0) == null) {
            currentId = 0;
        }
        else {
            currentId = tOrderDetailsList.get(0).getId();
        }
        currentId++;
        return CommonResult.success(currentId);
    }

    @ApiOperation(value = "获取订单总额")
    @RequestMapping(value = "/getOrderTotal", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getOrderTotal(@RequestBody Object orderId) {
        BigDecimal orderTotal;
        QueryWrapper<TOrderDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("SUM(total) as total").eq("order_id",orderId).eq("is_valid",0);

        List<TOrderDetails> tOrderDetailsList = tOrderDetailsService.findAll(queryWrapper);

        if (tOrderDetailsList.get(0) == null) {
            orderTotal = new BigDecimal(0);
        }
        else {
            orderTotal = tOrderDetailsList.get(0).getTotal();
        }
        return CommonResult.success(orderTotal);
    }
}

