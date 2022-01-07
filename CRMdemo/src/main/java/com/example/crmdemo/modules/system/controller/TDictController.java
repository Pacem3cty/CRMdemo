package com.example.crmdemo.modules.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.annoation.RequiredPermission;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.system.dto.TDictDTO;
import com.example.crmdemo.modules.system.model.TDict;
import com.example.crmdemo.modules.system.service.TDictService;
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
 * @since 2022-01-07
 */
@RestController
@RequestMapping("/dict")
public class TDictController {

    private TDictService tDictService;

    @Autowired
    public void settDictService(TDictService tDictService){
        this.tDictService = tDictService;
    }

    @RequiredPermission(code = "05011")
    @ApiOperation(value = "查询数据字典信息")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll(@RequestBody TDictDTO tDictDTO) {
        QueryWrapper<TDict> queryWrapper = new QueryWrapper<>();

        //数据类型
        if (tDictDTO.getDataValue()!= null && !tDictDTO.getDataValue().equals("")) {
            queryWrapper.like("data_value", tDictDTO.getDataValue());
        }

        //数据名称
        if (tDictDTO.getDataName() != null && !tDictDTO.getDataName().equals("")) {
            queryWrapper.like("data_name", tDictDTO.getDataName());
        }

        //数据值
        if (tDictDTO.getDataValue()!= null && !tDictDTO.getDataValue().equals("")) {
            queryWrapper.like("data_value", tDictDTO.getDataValue());
        }

        queryWrapper.eq("is_valid", 0); //查询没有被软删除的记录
        queryWrapper.orderByAsc("id");//按升序排序

        Page page = new Page();//分页查询page类
        page.setCurrent(tDictDTO.getCurrent());//获取当前页
        page.setSize(tDictDTO.getPageSize());//获取每页显示条目
        IPage<TDict> pageResult = tDictService.findAll(page, queryWrapper);
        return CommonResult.success(pageResult);
    }

    @RequiredPermission(code = "05012")
    @ApiOperation(value = "新增数据字典信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> add(@RequestBody TDict tDict) {
        return CommonResult.success(tDictService.add(tDict));
    }

    @RequiredPermission(code = "05013")
    @ApiOperation(value = "修改数据字典信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@RequestBody TDict tDict) {
        return CommonResult.success(tDictService.update(tDict));
    }

    @RequiredPermission(code = "05012")
    @ApiOperation(value = "获取新增角色编号")
    @RequestMapping(value = "/getCurrentId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCurrentId() {
        Integer currentId;
        QueryWrapper<TDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MAX(id) as id");
        List<TDict> tDictList = tDictService.findAll(queryWrapper);
        if (tDictList.get(0) == null) {
            currentId = 0;
        }
        else {
            currentId = tDictList.get(0).getId();
        }
        currentId++;
        return CommonResult.success(currentId);
    }

    @RequiredPermission(code = "05014")
    @ApiOperation(value = "软删除角色信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@RequestBody String ids) {
        JSONObject object = JSONObject.parseObject(ids);
        ids = object.get("ids").toString();
        return CommonResult.success(tDictService.updateById(ids));
    }

    @RequiredPermission(code = "05011")
    @ApiOperation(value = "查询数据名称")
    @RequestMapping(value = "/listDataName", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult listDataName() {
        QueryWrapper<TDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("data_name as dataName").eq("is_valid", 0).groupBy("data_name");
        List<Map<String, Object>> tDictList = tDictService.query(queryWrapper);
        return CommonResult.success(tDictList);
    }
}

