package com.example.crmdemo.modules.system.controller;


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

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wr
 * @since 2021-10-09
 */
@RestController
@RequestMapping("/role")
public class TRoleController {

    @Autowired
    TRoleService tRoleService;

    @ApiOperation(value= "查询角色信息")
    @RequestMapping(value = "/queryAll",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateInfo(@RequestBody TRoleDTO tRoleDTO) {
        QueryWrapper<TRole> queryWrapper = new QueryWrapper<>();

        //用户名称
        if(tRoleDTO.getRoleName() != null && !tRoleDTO.getRoleName().equals("")) {
            queryWrapper.like("role_name", tRoleDTO.getRoleName());
        }

        queryWrapper.eq("is_valid",0); //查询没有被软删除的记录
        queryWrapper.orderByAsc("id");//按升序排序

        Page page = new Page();//分页查询page类
        page.setCurrent(tRoleDTO.getCurrent());//获取当前页数
        page.setSize(tRoleDTO.getPageSize());//获取每页显示条目
        IPage<TRole> pageResult = tRoleService.findAll(page,queryWrapper);
        return CommonResult.success(pageResult);
    }

    @ApiOperation(value = "查询用户角色信息")
    @RequestMapping(value = "/listRoleName", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult listRoleName() {//不另提供筛选查询分配状态已分配的信息 避免冗余
        QueryWrapper<TRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id , role_name").groupBy("role_name").eq("is_valid",0);
        List<TRole> tRoleList =tRoleService.findAll(queryWrapper);
        return CommonResult.success(tRoleList);
    }


}

