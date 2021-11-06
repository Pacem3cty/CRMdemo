package com.example.crmdemo.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.system.dto.TRoleDTO;
import com.example.crmdemo.modules.system.model.TModule;
import com.example.crmdemo.modules.system.model.TRole;
import com.example.crmdemo.modules.system.model.ZTree;
import com.example.crmdemo.modules.system.service.TModuleService;
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
@RequestMapping("/module")
public class TModuleController {


    private TModuleService tModuleService;

    @Autowired
    public void settModuleService(TModuleService tModuleService) {
        this.tModuleService = tModuleService;
    }

    @ApiOperation(value = "查询资源信息")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll() {
        QueryWrapper<TModule> queryWrapper = new QueryWrapper<>();

        queryWrapper.select("id,parent_id as pid,module_name as name").eq("is_valid", 0);
//        queryWrapper.orderByAsc("id");//按升序排序
        List<Map<String, Object>> zTreeList = tModuleService.query(queryWrapper);
        return CommonResult.success(zTreeList);
    }

}

