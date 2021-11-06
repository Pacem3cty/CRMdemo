package com.example.crmdemo.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.sales.dto.TSaleChanceDto;
import com.example.crmdemo.modules.sales.model.TSaleChance;
import com.example.crmdemo.modules.system.model.TUserRole;
import com.example.crmdemo.modules.system.service.TUserRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wr
 * @since 2021-10-09
 */
@RestController
@RequestMapping("/userRole")
public class TUserRoleController {


    TUserRoleService tUserRoleService;

    @Autowired
    public void settUserRoleService(TUserRoleService tUserRoleService) {
        this.tUserRoleService = tUserRoleService;
    }

    @ApiOperation(value = "查询用户-角色信息")
    @RequestMapping(value = "/queryRoleId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryRoleId(@RequestBody Object userId) {
        Integer roleId = 0;
        QueryWrapper<TUserRole> queryWrapper = new QueryWrapper<>();

        //用户编号
        if (userId != null && !userId.equals("")) {
            queryWrapper.select("role_id").eq("user_id", userId);
        }
        List<TUserRole> tUserRoleList = tUserRoleService.findAll(queryWrapper);
        if (tUserRoleList.size() > 0) {
            roleId = tUserRoleList.get(0).getRoleId();
        }
        return CommonResult.success(roleId);
    }

    @ApiOperation(value = "新增用户-角色信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> add(@RequestBody TUserRole tUserRole) {
        return CommonResult.success(tUserRoleService.add(tUserRole));
    }

    @ApiOperation(value = "修改用户-角色信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@RequestBody TUserRole tUserRole) {
        return CommonResult.success(tUserRoleService.update(tUserRole));
    }
}

