package com.example.crmdemo.modules.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.system.dto.TRoleDTO;
import com.example.crmdemo.modules.system.dto.TUserDTO;
import com.example.crmdemo.modules.system.model.TRole;
import com.example.crmdemo.modules.system.model.TUser;
import com.example.crmdemo.modules.system.service.TRoleService;
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
 * @since 2021-10-09
 */
@RestController
@RequestMapping("/role")
public class TRoleController {


    TRoleService tRoleService;

    @Autowired
    public void settRoleService(TRoleService tRoleService) {
        this.tRoleService = tRoleService;
    }

    @ApiOperation(value = "查询角色信息")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll(@RequestBody TRoleDTO tRoleDTO) {
        QueryWrapper<TRole> queryWrapper = new QueryWrapper<>();

        //用户名称
        if (tRoleDTO.getRoleName() != null && !tRoleDTO.getRoleName().equals("")) {
            queryWrapper.like("role_name", tRoleDTO.getRoleName());
        }

        queryWrapper.eq("is_valid", 0); //查询没有被软删除的记录
        queryWrapper.orderByAsc("id");//按升序排序

        Page page = new Page();//分页查询page类
        page.setCurrent(tRoleDTO.getCurrent());//获取当前页数
        page.setSize(tRoleDTO.getPageSize());//获取每页显示条目
        IPage<TRole> pageResult = tRoleService.findAll(page, queryWrapper);
        return CommonResult.success(pageResult);
    }

    @ApiOperation(value = "查询角色编号、角色名称信息")
    @RequestMapping(value = "/listRoleName", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult listRoleName() {
        QueryWrapper<TRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id , role_name as roleName").groupBy("role_name").eq("is_valid", 0);
        List<Map<String, Object>> tRoleList = tRoleService.query(queryWrapper);
        return CommonResult.success(tRoleList);
    }

    @ApiOperation(value = "新增角色信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> add(@RequestBody TRole tRole) {
        return CommonResult.success(tRoleService.add(tRole));
    }

    @ApiOperation(value = "修改角色信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@RequestBody TRole tRole) {
        return CommonResult.success(tRoleService.update(tRole));
    }

    @ApiOperation(value = "获取新增角色编号")
    @RequestMapping(value = "/getCurrentId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCurrentId() {
        Integer currentId = 0;
        QueryWrapper<TRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MAX(id) as id");
        List<TRole> tRoleList = tRoleService.findAll(queryWrapper);
        if (tRoleList.size() > 0) {
            currentId = tRoleList.get(0).getId();
        }
        currentId++;
        return CommonResult.success(currentId);
    }

    @ApiOperation(value = "软删除角色信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@RequestBody String ids) {
        JSONObject object = JSONObject.parseObject(ids);
        ids = object.get("ids").toString();
        return CommonResult.success(tRoleService.updateById(ids));
    }

    @ApiOperation(value = "查询角色名称")
    @RequestMapping(value = "/queryRoleName", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryRoleName(@RequestBody Object roleId) {
      String roleName ="";
      QueryWrapper<TRole> queryWrapper = new QueryWrapper<>();
      queryWrapper.select("role_Name").eq("id",roleId).eq("is_valid",0);
      List<TRole> tRoleList = tRoleService.findAll(queryWrapper);
        if (tRoleList.size() > 0) {
            roleName = tRoleList.get(0).getRoleName();
        }
        return CommonResult.success(roleName);
    }
}

