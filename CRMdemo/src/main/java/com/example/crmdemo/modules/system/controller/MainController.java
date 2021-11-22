package com.example.crmdemo.modules.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.crmdemo.modules.system.model.TPermission;
import com.example.crmdemo.modules.system.model.TUserRole;
import com.example.crmdemo.modules.system.service.TPermissionService;
import com.example.crmdemo.modules.system.service.TRoleService;
import com.example.crmdemo.modules.system.service.TUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
@RequestMapping("")
public class MainController {

    @Resource
    HttpSession session;

    private TUserRoleService tUserRoleService;

    @Autowired
    public void settUserRoleService(TUserRoleService tUserRoleService){
        this.tUserRoleService = tUserRoleService;
    }

    private TPermissionService tPermissionService;

    @Autowired
    public void settPermissionService(TPermissionService tPermissionService){
        this.tPermissionService = tPermissionService;
    }

    @RequestMapping("/main")
    public void main(@RequestBody Object userId,HttpServletRequest request){

        Integer roleId = 0;

        List<String> permissionsList = new ArrayList<>();

        QueryWrapper<TUserRole> tUserRoleQueryWrapper= new QueryWrapper<>();
        tUserRoleQueryWrapper.select("role_id").eq("user_id",userId);
        List<TUserRole> tUserRoleList = tUserRoleService.findAll(tUserRoleQueryWrapper);
        System.err.println(tUserRoleList);
        if(tUserRoleList.get(0) != null){
            roleId = tUserRoleList.get(0).getRoleId();
        }

        QueryWrapper<TPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("acl_value").eq("role_id",roleId);
        List<Map<String,Object>> permissions = tPermissionService.query(queryWrapper);

        for (Map<String, Object> m : permissions)
        {//遍历List中的键值对对象
            for (Object v : m.values())
            {//遍历Map<String,Object>中的value值
                permissionsList.add((String)v);
            }

        }
        request.getSession().setAttribute("permissionsList",permissionsList);

        System.err.println("权限列表为："+permissionsList);
    }
}
