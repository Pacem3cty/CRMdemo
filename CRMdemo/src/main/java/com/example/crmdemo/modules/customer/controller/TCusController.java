package com.example.crmdemo.modules.customer.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.customer.dto.TCusDTO;
import com.example.crmdemo.modules.customer.model.TCus;
import com.example.crmdemo.modules.customer.service.TCusService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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
@RequestMapping("/cus")
public class TCusController {
    Integer currentId;

    private TCusService tCusService;

    @Autowired
    public void settCusService(TCusService tCusService){
        this.tCusService = tCusService;
    }

    @ApiOperation(value = "查询用户信息")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll(@RequestBody TCusDTO tCusDTO) {
        QueryWrapper<TCus> queryWrapper = new QueryWrapper<>();

        //客户名称
        if (tCusDTO.getCusName() != null && !tCusDTO.getCusName().equals("")) {
            queryWrapper.like("cus_name", tCusDTO.getCusName());
        }

        //客户编号
        if (tCusDTO.getCusNum() != null && !tCusDTO.getCusNum().equals("")) {
            queryWrapper.eq("cus_num", tCusDTO.getCusNum());
        }

        queryWrapper.eq("is_valid", 0); //查询没有被软删除的记录
        queryWrapper.orderByAsc("id");//按升序排序

        Page page = new Page();//分页查询page类
        page.setCurrent(tCusDTO.getCurrent());//获取当前记录索引值
        page.setSize(tCusDTO.getPageSize());//获取每页显示条目
        IPage<TCus> pageResult = tCusService.findAll(page, queryWrapper);
        return CommonResult.success(pageResult);
    }

    @ApiOperation(value = "新增客户信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> add(@RequestBody TCus tCus) {
        return CommonResult.success(tCusService.add(tCus));
    }

    @ApiOperation(value = "修改客户信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@RequestBody TCus tCus) {
        return CommonResult.success(tCusService.update(tCus));
    }

    @ApiOperation(value = "软删除客户信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@RequestBody String ids) {
        JSONObject object = JSONObject.parseObject(ids);
        ids = object.get("ids").toString();
        return CommonResult.success(tCusService.updateById(ids));
    }

    @ApiOperation(value = "获取新增客户序号")
    @RequestMapping(value = "/getCurrentId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCurrentId() {
        QueryWrapper<TCus> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MAX(id) as id");
        List<TCus> tCusList = tCusService.findAll(queryWrapper);
        if (tCusList.get(0) == null) {
            currentId = 0;
        }
        else {
            currentId = tCusList.get(0).getId();
        }
        currentId++;
        return CommonResult.success(currentId);
    }

    @ApiOperation(value = "获取新增客户编号")
    @RequestMapping(value = "/getCusNum", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCusNum() {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        QueryWrapper<TCus> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MAX(id) as id");
        List<TCus> tCusList = tCusService.findAll(queryWrapper);
        if (tCusList.get(0) == null) {
            currentId = 0;
        }
        else {
            currentId = tCusList.get(0).getId();
        }
        currentId++;
        String cusNum = "C"+date+String.format("%07d", currentId);//生成由日期+前位补0的7位序号码组成的客户编号
        return CommonResult.success(cusNum);
    }

    @ApiOperation(value = "查询客户名称信息")
    @RequestMapping(value = "/listCusName", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult listCusName() {
        QueryWrapper<TCus> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("cus_name as cusName").eq("is_valid", 0).groupBy("cus_name");//GROUP BY 较 DISTINCT 性能更优
        List<Map<String, Object>> tCusList = tCusService.query(queryWrapper);
        return CommonResult.success(tCusList);
    }
}

