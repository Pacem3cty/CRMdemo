package com.example.crmdemo.modules.customer.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.customer.dto.TCusContactDTO;
import com.example.crmdemo.modules.customer.model.TCusContact;
import com.example.crmdemo.modules.customer.service.TCusContactService;
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
@RequestMapping("/cusContact")
public class TCusContactController {

    private TCusContactService tCusContactService;

    @Autowired
    public void settCusContactService(TCusContactService tCusContactService){
        this.tCusContactService = tCusContactService;
    }

    @ApiOperation(value = "查询客户交往记录信息")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll(@RequestBody TCusContactDTO tCusContactDTO) {
        QueryWrapper<TCusContact> queryWrapper = new QueryWrapper<>();

        //客户编号
        if (tCusContactDTO.getCusId() != null && !tCusContactDTO.getCusId().equals("")) {
            queryWrapper.eq("cus_id", tCusContactDTO.getCusId());
        }

        queryWrapper.eq("is_valid", 0); //查询没有被软删除的记录
        queryWrapper.orderByAsc("id");//按升序排序

        Page page = new Page();//分页查询page类
        page.setCurrent(tCusContactDTO.getCurrent());//获取当前页数
        page.setSize(tCusContactDTO.getPageSize());//获取每页显示条目
        IPage<TCusContact> pageResult = tCusContactService.findAll(page, queryWrapper);
        return CommonResult.success(pageResult);
    }

    @ApiOperation(value = "新增客户交往记录信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> add(@RequestBody TCusContact tCusContact) {
        return CommonResult.success(tCusContactService.add(tCusContact));
    }

    @ApiOperation(value = "修改客户交往记录信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@RequestBody TCusContact tCusContact) {
        return CommonResult.success(tCusContactService.update(tCusContact));
    }

    @ApiOperation(value = "软删除客户交往记录信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@RequestBody String ids) {
        JSONObject object = JSONObject.parseObject(ids);
        ids = object.get("ids").toString();
        return CommonResult.success(tCusContactService.updateById(ids));
    }

    @ApiOperation(value = "获取新增客户交往记录序号")
    @RequestMapping(value = "/getCurrentId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCurrentId() {
        Integer currentId;
        QueryWrapper<TCusContact> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MAX(id) as id");
        List<TCusContact> tCusContactList = tCusContactService.findAll(queryWrapper);
        if (tCusContactList.get(0) == null) {
            currentId = 0;
        }
        else {
            currentId = tCusContactList.get(0).getId();
        }
        currentId++;
        return CommonResult.success(currentId);
    }

}

