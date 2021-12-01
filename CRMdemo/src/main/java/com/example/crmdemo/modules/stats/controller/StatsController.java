package com.example.crmdemo.modules.stats.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.stats.dto.CusCTRBDTO;
import com.example.crmdemo.modules.stats.model.CusCTRB;
import com.example.crmdemo.modules.stats.service.CusCTRBService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private CusCTRBService cusCTRBService;

    @Autowired
    public void setCusCTRBService(CusCTRBService cusCTRBService){
        this.cusCTRBService = cusCTRBService;
    }

    @ApiOperation(value = "查询客户贡献度信息")
    @RequestMapping(value = "/listCusCTRB", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll(@RequestBody CusCTRBDTO cusCTRBDTO) {
        Page<CusCTRB> page = new Page<>(cusCTRBDTO.getCurrent(), cusCTRBDTO.getPageSize());

        List<CusCTRB> result = cusCTRBService.findCusCTRB(page, new QueryWrapper<>());

        page.setRecords(result);//将查询结果加入List<Page<CusCTRB>>列表中

        return CommonResult.success(page);
    }

}
