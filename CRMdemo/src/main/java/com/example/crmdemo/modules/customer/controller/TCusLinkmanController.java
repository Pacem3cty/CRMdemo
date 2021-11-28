package com.example.crmdemo.modules.customer.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.api.CommonResult;

import com.example.crmdemo.modules.customer.dto.TCusLinkmanDTO;
import com.example.crmdemo.modules.customer.model.TCusContact;
import com.example.crmdemo.modules.customer.model.TCusLinkman;
import com.example.crmdemo.modules.customer.service.TCusLinkmanService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/cusLinkman")
public class TCusLinkmanController {

    private TCusLinkmanService tCusLinkmanService;

    @Autowired
    public void settCusLinkmanService(TCusLinkmanService tCusLinkmanService){
        this.tCusLinkmanService = tCusLinkmanService;
    }

    @ApiOperation(value = "查询客户联系人信息")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll(@RequestBody TCusLinkmanDTO tCusLinkmanDTO) {
        QueryWrapper<TCusLinkman> queryWrapper = new QueryWrapper<>();

        //客户编号
        if (tCusLinkmanDTO.getCusId() != null && !tCusLinkmanDTO.getCusId().equals("")) {
            queryWrapper.eq("cus_id", tCusLinkmanDTO.getCusId());
        }

        queryWrapper.eq("is_valid", 0); //查询没有被软删除的记录
        queryWrapper.orderByAsc("id");//按升序排序

        Page page = new Page();//分页查询page类
        page.setCurrent(tCusLinkmanDTO.getCurrent());//获取当前页数
        page.setSize(tCusLinkmanDTO.getPageSize());//获取每页显示条目
        IPage<TCusContact> pageResult = tCusLinkmanService.findAll(page, queryWrapper);
        return CommonResult.success(pageResult);
    }

    @ApiOperation(value = "新增客户联系人信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> add(@RequestBody TCusLinkman tCusLinkman) {
        return CommonResult.success(tCusLinkmanService.add(tCusLinkman));
    }

    @ApiOperation(value = "修改客户联系人信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@RequestBody TCusLinkman tCusLinkman) {
        return CommonResult.success(tCusLinkmanService.update(tCusLinkman));
    }

    @ApiOperation(value = "软删除客户联系人信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@RequestBody String ids) {
        JSONObject object = JSONObject.parseObject(ids);
        ids = object.get("ids").toString();
        return CommonResult.success(tCusLinkmanService.updateById(ids));
    }

    @ApiOperation(value = "获取新增客户联系人序号")
    @RequestMapping(value = "/getCurrentId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCurrentId() {
        Integer currentId;
        QueryWrapper<TCusLinkman> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MAX(id) as id");
        List<TCusLinkman> cusLinkmanList = tCusLinkmanService.findAll(queryWrapper);
        if (cusLinkmanList.get(0) == null) {
            currentId = 0;
        }
        else {
            currentId = cusLinkmanList.get(0).getId();
        }
        currentId++;
        return CommonResult.success(currentId);
    }
}

