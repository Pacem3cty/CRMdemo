package com.example.crmdemo.modules.sales.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.annoation.RequiredPermission;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.sales.dto.TSaleChanceDto;
import com.example.crmdemo.modules.sales.model.TSaleChance;
import com.example.crmdemo.modules.sales.service.TSaleChanceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wr
 * @since 2021-10-20
 */

@RestController
@RequestMapping("/sales")
public class TSaleChanceController {


    private TSaleChanceService tSaleChanceService;

    @Autowired
    public void settSaleChanceService(TSaleChanceService tSaleChanceService) {
        this.tSaleChanceService = tSaleChanceService;
    }

    @RequiredPermission(code = "01011")
    @ApiOperation(value = "查询创建人信息")
    @RequestMapping(value = "/listCreatePerson", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult listCreatePerson() {//不另提供筛选查询分配状态已分配的信息 避免冗余
        QueryWrapper<TSaleChance> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("DISTINCT create_person").eq("is_valid", 0);
        queryWrapper.select("create_person as createPerson").eq("is_valid", 0).groupBy("create_person");//GROUP BY 较 DISTINCT 性能更优
//        List<TSaleChance> tSaleChanceList = tSaleChanceService.findAll(queryWrapper);
        List<Map<String, Object>> tSaleChanceList = tSaleChanceService.query(queryWrapper);
        return CommonResult.success(tSaleChanceList);
    }


    @RequiredPermission(code = "01011")
    @ApiOperation(value = "查询客户名称信息")
    @RequestMapping(value = "/listCustomerName", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult listCustomerName() {
        QueryWrapper<TSaleChance> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("customer_name as customerName").eq("is_valid", 0).groupBy("customer_name");//GROUP BY 较 DISTINCT 性能更优
        List<Map<String, Object>> tSaleChanceList = tSaleChanceService.query(queryWrapper);
        return CommonResult.success(tSaleChanceList);
    }

    @RequiredPermission(code = "01012")
    @ApiOperation(value = "获取新增营销机会编号")
    @RequestMapping(value = "/getCurrentId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCurrentId() {
        Integer currentId;
        QueryWrapper<TSaleChance> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MAX(id) as id");
        List<TSaleChance> tSaleChanceList = tSaleChanceService.findAll(queryWrapper);
        if (tSaleChanceList.get(0) == null) {
            currentId = 0;
        }
        else {
            currentId = tSaleChanceList.get(0).getId();
        }
        currentId++;
        return CommonResult.success(currentId);
    }

    @RequiredPermission(code = "01011")
    @ApiOperation(value = "查询营销机会信息")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll(@RequestBody TSaleChanceDto tSaleChanceDto) {
        QueryWrapper<TSaleChance> queryWrapper = new QueryWrapper<>();

        //客户名
        if (tSaleChanceDto.getCustomerName() != null && !tSaleChanceDto.getCustomerName().equals("")) {
            queryWrapper.like("customer_name", tSaleChanceDto.getCustomerName());

        }
        //创建人
        if (tSaleChanceDto.getCreatePerson() != null && !tSaleChanceDto.getCreatePerson().equals("")) {
            queryWrapper.like("create_person", tSaleChanceDto.getCreatePerson());

        }
        //分配状态
        if (tSaleChanceDto.getState() != null && !tSaleChanceDto.getState().equals("")) {
            queryWrapper.eq("state", tSaleChanceDto.getState());
        }

        //建档日
        if (tSaleChanceDto.getStartTime() != null && !tSaleChanceDto.getStartTime().equals("")) {
            //格式化时间
            queryWrapper.ge("create_date", tSaleChanceDto.getStartTime());
            queryWrapper.le("create_date", tSaleChanceDto.getEndTime());
        }
        queryWrapper.eq("is_valid", 0); //查询没有被软删除的记录
        queryWrapper.orderByAsc("id");//按升序排序

        Page page = new Page();//分页查询page类
        page.setCurrent(tSaleChanceDto.getCurrent());//获取当前页
        page.setSize(tSaleChanceDto.getPageSize());//获取每页显示条目
        IPage<TSaleChance> pageResult = tSaleChanceService.findAll(page, queryWrapper);
        return CommonResult.success(pageResult);
    }

    @RequiredPermission(code = "01012")
    @ApiOperation(value = "新增营销机会信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> add(@RequestBody TSaleChance tSaleChance) {
        return CommonResult.success(tSaleChanceService.add(tSaleChance));
    }

    @RequiredPermission(code = "01013")
    @ApiOperation(value = "修改营销机会信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@RequestBody TSaleChance tSaleChance) {
        return CommonResult.success(tSaleChanceService.update(tSaleChance));
    }

    @RequiredPermission(code = "01014")
    @ApiOperation(value = "物理删除营销机会信息")
    @RequestMapping(value = "/deletePhysical", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> deleteWl(@RequestBody String ids) {
        JSONObject object = JSONObject.parseObject(ids);
        ids = object.get("ids").toString();
        return CommonResult.success(tSaleChanceService.delete(ids));
    }

    @RequiredPermission(code = "01014")
    @ApiOperation(value = "软删除营销机会信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@RequestBody String ids) {
        JSONObject object = JSONObject.parseObject(ids);
        ids = object.get("ids").toString();
        return CommonResult.success(tSaleChanceService.updateById(ids));
    }

    @RequiredPermission(code = "01011")
    @ApiOperation(value = "查询营销机会信息（分配状态为已分配）")
    @RequestMapping(value = "/queryIsAssigned", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryIsAssigned(@RequestBody TSaleChanceDto tSaleChanceDto) {
        QueryWrapper<TSaleChance> queryWrapper = new QueryWrapper<>();

        //客户名
        if (tSaleChanceDto.getCustomerName() != null && !tSaleChanceDto.getCustomerName().equals("")) {
            queryWrapper.like("customer_name", tSaleChanceDto.getCustomerName());

        }
        //创建人
        if (tSaleChanceDto.getCreatePerson() != null && !tSaleChanceDto.getCreatePerson().equals("")) {
            queryWrapper.like("create_person", tSaleChanceDto.getCreatePerson());

        }
        //分配状态
        if (tSaleChanceDto.getDevResult() != null && !tSaleChanceDto.getDevResult().equals("")) {
            queryWrapper.eq("dev_result", tSaleChanceDto.getDevResult());
        }

        //建档日
        if (tSaleChanceDto.getStartTime() != null && !tSaleChanceDto.getStartTime().equals("")) {
            //格式化时间
            queryWrapper.ge("create_date", tSaleChanceDto.getStartTime());
            queryWrapper.le("create_date", tSaleChanceDto.getEndTime());
        }
        queryWrapper.eq("state", 1);//查询分配状态为已分配的记录
        queryWrapper.eq("is_valid", 0); //查询没有被软删除的记录
        queryWrapper.orderByAsc("id");//按升序排序

        Page page = new Page();//分页查询page类
        page.setCurrent(tSaleChanceDto.getCurrent());//获取当前页
        page.setSize(tSaleChanceDto.getPageSize());//获取每页显示条目
        IPage<TSaleChance> pageResult = tSaleChanceService.findAll(page, queryWrapper);
        return CommonResult.success(pageResult);
    }
}

