package com.example.crmdemo.modules.service.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.annoation.RequiredPermission;
import com.example.crmdemo.common.api.CommonResult;



import com.example.crmdemo.modules.service.dto.TCusServiceDto;
import com.example.crmdemo.modules.service.model.TCusService;
import com.example.crmdemo.modules.service.service.TCusServiceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wr
 * @since 2021-12-03
 */
@RestController
@RequestMapping("/service")
public class TCusServiceController {


    private TCusServiceService tCusServiceService;

    @Autowired
    public void settCusServiceService(TCusServiceService tCusServiceService){
        this.tCusServiceService = tCusServiceService;
    }

    @RequiredPermission(code = "0301")
    @ApiOperation(value = "查询客户名称信息")
    @RequestMapping(value = "/listCustomerName", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult listCustomerName() {
        QueryWrapper<TCusService> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("customer").eq("is_valid", 0).groupBy("customer");//GROUP BY 较 DISTINCT 性能更优
        List<Map<String, Object>> tServiceList = tCusServiceService.query(queryWrapper);
        return CommonResult.success(tServiceList);
    }

    @RequiredPermission(code = "0302")
    @ApiOperation(value = "获取新增服务编号")
    @RequestMapping(value = "/getCurrentId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCurrentId() {
        Integer currentId;

        QueryWrapper<TCusService> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MAX(id) as id");

        List<TCusService> tServiceList = tCusServiceService.findAll(queryWrapper);
        if (tServiceList.get(0) == null) {
            currentId = 0;
        }
        else {
            currentId = tServiceList.get(0).getId();
        }
        currentId++;
        return CommonResult.success(currentId);
    }

    @RequiredPermission(code = "0301")
    @ApiOperation(value = "查询服务信息")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll(@RequestBody TCusServiceDto tCusServiceDto) {
        QueryWrapper<TCusService> queryWrapper = new QueryWrapper<>();

        //客户名
        if (tCusServiceDto.getCustomer() != null && !tCusServiceDto.getCustomer().equals("")) {
            queryWrapper.like("customer", tCusServiceDto.getCustomer());

        }
        //服务类型
        if (tCusServiceDto.getServiceType() != null && !tCusServiceDto.getServiceType().equals("")) {
            queryWrapper.eq("service_type", tCusServiceDto.getServiceType());
        }

        //服务状态
        if (tCusServiceDto.getState() != null && !tCusServiceDto.getState().equals("")) {
            queryWrapper.eq("state", tCusServiceDto.getState());
        }

        //服务状态
        if (tCusServiceDto.getAssignPerson() != null && !tCusServiceDto.getAssignPerson().equals("")) {
            queryWrapper.eq("assign_person", tCusServiceDto.getAssignPerson());
        }

        queryWrapper.eq("is_valid", 0); //查询没有被软删除的记录
        queryWrapper.orderByAsc("id");//按升序排序

        Page page = new Page();//分页查询page类
        page.setCurrent(tCusServiceDto.getCurrent());//获取当前页
        page.setSize(tCusServiceDto.getPageSize());//获取每页显示条目
        IPage<TCusService> pageResult = tCusServiceService.findAll(page, queryWrapper);
        return CommonResult.success(pageResult);
    }

    @RequiredPermission(code = "0302")
    @ApiOperation(value = "新增服务信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> add(@RequestBody TCusService tCusService) {
        return CommonResult.success(tCusServiceService.add(tCusService));
    }


    @RequiredPermission(code = "0303")
    @ApiOperation(value = "修改服务信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@RequestBody TCusService tCusService) {
        return CommonResult.success(tCusServiceService.update(tCusService));
    }


    @RequiredPermission(code = "0304")
    @ApiOperation(value = "软删除服务信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@RequestBody String ids) {
        JSONObject object = JSONObject.parseObject(ids);
        ids = object.get("ids").toString();
        return CommonResult.success(tCusServiceService.updateById(ids));
    }

    @RequiredPermission(code = "0303")
    @ApiOperation(value = "分配/处理/反馈/归档操作")
    @RequestMapping(value = "/updatePart", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePart(@RequestBody String jsonStr) {
        TCusService tCusService = new TCusService();

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Integer id = jsonObject.getInteger("id");//服务编号

//        String customer = jsonObject.getString("customer");//客户名称
//        String serviceRequest = jsonObject.getString("serviceRequest");//服务内容
//        String overview = jsonObject.getString("overview");//服务概述
        String assignPerson = jsonObject.getString("assignPerson");//分配人

        String serviceProcess = jsonObject.getString("serviceProcess");//处理内容
        String processPerson = jsonObject.getString("processPerson");//处理人

        String csr = jsonObject.getString("csr");//处理人
        String processResult = jsonObject.getString("processResult");//处理人

        Integer state =jsonObject.getInteger("state");//服务状态

        UpdateWrapper<TCusService> updateWrapper = new UpdateWrapper<>();

//        updateWrapper.set("customer", customer).set("service_request", serviceRequest).set("overview", overview);
        updateWrapper.set("assign_person", assignPerson).set("assign_date",new Date());
        updateWrapper.set("process_person",processPerson).set("service_process", serviceProcess).set("process_date",new Date());
        updateWrapper.set("process_result",processResult).set("csr",csr);
        updateWrapper.set("state", state).eq("id", id).set("update_date",new Date()).eq("is_valid", 0);

        return CommonResult.success(tCusServiceService.updatePart(tCusService,updateWrapper));
    }
}

