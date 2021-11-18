package com.example.crmdemo.modules.system.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.system.model.TModule;
import com.example.crmdemo.modules.system.model.TPermission;
import com.example.crmdemo.modules.system.service.TModuleService;
import com.example.crmdemo.modules.system.service.TPermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wr
 * @since 2021-10-09
 */
@RestController
@RequestMapping("/permission")
public class TPermissionController {

    private TPermissionService tPermissionService;

    @Autowired
    public void settPermissionService(TPermissionService tPermissionService) {
        this.tPermissionService = tPermissionService;
    }

    private TModuleService tModuleService;

    @Autowired
    public void settModuleService(TModuleService tModuleService) {
        this.tModuleService = tModuleService;
    }

    @ApiOperation(value = "查询已授权角色资源信息（加载树形控件中默认选框）")
    @RequestMapping(value = "/loadData", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult loadData(@RequestBody Object roleId){
        QueryWrapper<TPermission> queryWrapper = new QueryWrapper<>();

        queryWrapper.select("module_id").eq("role_id",roleId);

        List<TPermission> moduleIdList = tPermissionService.findAll(queryWrapper);


        Object[] moduleIdArray = new Object[moduleIdList.size()];

        for(int i = 0; i < moduleIdList.size();i++){//转换为数组形式返回
            moduleIdArray[i] = moduleIdList.get(i).getModuleId();
        }

        return CommonResult.success(moduleIdArray);
    }

    @ApiOperation(value = "角色授权资源")
    @RequestMapping(value = "/authModules", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult authModules(@RequestBody String jsonStr){
        AtomicBoolean flg = new AtomicBoolean(true);

        Integer currentId;

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Integer roleId = jsonObject.getInteger("roleId");//角色编号

        QueryWrapper<TPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        Integer count = tPermissionService.selectCount(queryWrapper);
        if (count > 0) {//操作表中已授权的记录时 先将表中已有的删除
            tPermissionService.delete(String.valueOf(roleId));
        }

        QueryWrapper<TPermission> queryWrapper0 = new QueryWrapper<>();
        queryWrapper0.select("MAX(id) as id");
        List<TPermission> tPermissions = tPermissionService.findAll(queryWrapper0);
        if (tPermissions.get(0) == null) {//当查询结果为空时
            currentId = 0;
        }
        else {
            currentId = tPermissions.get(0).getId();
        }

        JSONArray moduleIdsJSON = jsonObject.getJSONArray("moduleIds");
        Object[] moduleIds= moduleIdsJSON.toArray();//授权资源编号数组

        Integer[] mIds = new Integer[moduleIds.length];
        for(int b = 0; b < moduleIds.length; b++){
            mIds[b] = Integer.parseInt(moduleIds[b].toString());
        }

        if(mIds != null && mIds.length > 0){//若存在权限记录则添加

            for(Integer mId : mIds){
                TPermission tPermission = new TPermission();
                tPermission.setId(++currentId);
                tPermission.setRoleId(roleId);
                tPermission.setModuleId(mId);

                if(tModuleService.selectOne(new QueryWrapper<TModule>().eq("id", mId)).getOptValue() == " " || tModuleService.selectOne(new QueryWrapper<TModule>().eq("id", mId)).getOptValue() == null){
                    flg.set(false);
                    break;
                }

                tPermission.setAclValue(tModuleService.selectOne(new QueryWrapper<TModule>().eq("id", mId)).getOptValue());
                tPermission.setCreateDate(new Date());
                tPermission.setUpdateDate(new Date());

                flg.set(tPermissionService.add(tPermission));
            }
        }
       return CommonResult.success(flg);
    }

}

