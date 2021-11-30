package com.example.crmdemo.modules.customer.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.customer.dto.TCusOrderDTO;

import com.example.crmdemo.modules.customer.model.TCusOrder;
import com.example.crmdemo.modules.customer.service.TCusOrderService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wr
 * @since 2021-11-29
 */
@RestController
@RequestMapping("/cusOrder")
public class TCusOrderController {

    Integer currentId;

    private TCusOrderService tCusOrderService;

    @Autowired
    public void settCusOrderService(TCusOrderService tCusOrderService){
        this.tCusOrderService = tCusOrderService;
    }

    @ApiOperation(value = "查询客户订单记录信息")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll(@RequestBody TCusOrderDTO tCusOrderDTO) {
        QueryWrapper<TCusOrder> queryWrapper = new QueryWrapper<>();

        //客户编号
        if (tCusOrderDTO.getCusId() != null && !tCusOrderDTO.getCusId().equals("")) {
            queryWrapper.eq("cus_id", tCusOrderDTO.getCusId());
        }

        queryWrapper.eq("is_valid", 0); //查询没有被软删除的记录
        queryWrapper.orderByAsc("id");//按升序排序

        Page page = new Page();//分页查询page类
        page.setCurrent(tCusOrderDTO.getCurrent());//获取当前页数
        page.setSize(tCusOrderDTO.getPageSize());//获取每页显示条目
        IPage<TCusOrder> pageResult = tCusOrderService.findAll(page, queryWrapper);
        return CommonResult.success(pageResult);
    }

    @ApiOperation(value = "新增客户订单记录信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> add(@RequestBody TCusOrder tCusOrder) {
        return CommonResult.success(tCusOrderService.add(tCusOrder));
    }

    @ApiOperation(value = "修改客户订单记录信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@RequestBody TCusOrder tCusOrder) {
        return CommonResult.success(tCusOrderService.update(tCusOrder));
    }

    @ApiOperation(value = "软删除客户订单记录信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@RequestBody String ids) {
        JSONObject object = JSONObject.parseObject(ids);
        ids = object.get("ids").toString();
        return CommonResult.success(tCusOrderService.updateById(ids));
    }

    @ApiOperation(value = "获取新增客户订单记录序号")
    @RequestMapping(value = "/getCurrentId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCurrentId() {
        QueryWrapper<TCusOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MAX(id) as id");
        List<TCusOrder> tCusContactList = tCusOrderService.findAll(queryWrapper);
        if (tCusContactList.get(0) == null) {
            currentId = 0;
        }
        else {
            currentId = tCusContactList.get(0).getId();
        }
        currentId++;
        return CommonResult.success(currentId);
    }

    @ApiOperation(value = "标记为已支付")
    @RequestMapping(value = "/signPaid", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult signPaid(@RequestBody String jsonStr) {
        TCusOrder tCusOrder = new TCusOrder();

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Integer id = jsonObject.getInteger("id");//角色编号
        Date updateDate = jsonObject.getDate("updateDate");

        UpdateWrapper<TCusOrder> updateWrapper = new UpdateWrapper<>();

        updateWrapper.set("state", 1).set("update_date",updateDate).eq("id", id).eq("is_valid", 0);

        return CommonResult.success(tCusOrderService.updatePart(tCusOrder,updateWrapper));
    }

    @ApiOperation(value = "获取新增订单编号")
    @RequestMapping(value = "/getOrderNum", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCusNum() {
        QueryWrapper<TCusOrder> queryWrapper = new QueryWrapper<>();

        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());

        queryWrapper.select("MAX(id) as id");
        List<TCusOrder> tCusList = tCusOrderService.findAll(queryWrapper);
        if (tCusList.get(0) == null) {
            currentId = 0;
        }
        else {
            currentId = tCusList.get(0).getId();
        }
        currentId++;
        String orderNum = "O"+date+String.format("%07d", currentId);//生成由日期+前位补0的7位序号码组成的客户编号
        return CommonResult.success(orderNum);
    }

}


