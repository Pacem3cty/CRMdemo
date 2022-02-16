package com.example.crmdemo.modules.stats.controller;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.annoation.RequiredPermission;
import com.example.crmdemo.common.api.CommonResult;

import com.example.crmdemo.modules.stats.dto.CusCTRBDto;
import com.example.crmdemo.modules.stats.model.CusCTRB;
import com.example.crmdemo.modules.stats.service.CusCTRBService;
import com.example.crmdemo.modules.stats.service.ServiceCTRBService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private CusCTRBService cusCTRBService;

    private ServiceCTRBService serviceCTRBService;

    @Autowired
    public void setCusCTRBService(CusCTRBService cusCTRBService){
        this.cusCTRBService = cusCTRBService;
    }

    @Autowired
    public void setServiceCTRBService(ServiceCTRBService serviceCTRBService){this.serviceCTRBService = serviceCTRBService;}

    @RequiredPermission(code = "0401")
    @ApiOperation(value = "查询客户贡献度信息")
    @RequestMapping(value = "/listCusCTRB", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll(@RequestBody CusCTRBDto cusCTRBDTO) {

//        QueryWrapper<CusCTRB> queryWrapper = new QueryWrapper<>();
//
//        //客户名称
//        if (cusCTRBDTO.getCusName() != null && !cusCTRBDTO.getCusName().equals("")) {
//            queryWrapper.like("cus_name", cusCTRBDTO.getCusName());
//        }
//
//        //客户经理
//        if (cusCTRBDTO.getCusManager() != null && !cusCTRBDTO.getCusManager().equals("")) {
//            queryWrapper.eq("cus_manager", cusCTRBDTO.getCusManager());
//        }
//        //客户电话
//        if (cusCTRBDTO.getCusPhone() != null && !cusCTRBDTO.getCusPhone().equals("")) {
//            queryWrapper.eq("cus_phone", cusCTRBDTO.getCusPhone());
//        }
//
//        queryWrapper.eq("t_cus_order.state = 1 AND t_cus.is_valid = 0 AND t_cus_order.is_valid = 0 AND t_cus.id","cus_id").groupBy("t_cus_order.cus_id");

        Page page = new Page();//分页查询page类
        page.setCurrent(cusCTRBDTO.getCurrent());//获取当前页
        page.setSize(cusCTRBDTO.getPageSize());//获取每页显示条目

        List<CusCTRB> result = cusCTRBService.findCusCTRB(page, cusCTRBDTO);

//        List<CusCTRB> result = cusCTRBService.findCusCTRB(page, queryWrapper);

        page.setRecords(result);//将查询结果加入List<Page<CusCTRB>>列表中

        return CommonResult.success(page);
    }

    @RequiredPermission(code = "0401")
    @ApiOperation(value = "查询客户统计信息")
    @RequestMapping(value = "/listOrderStats", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryOrderStats(){
        return CommonResult.success(cusCTRBService.getOrderStats());
    }

    @RequiredPermission(code = "0401")
    @ApiOperation(value = "查询客户订单总金额统计信息")
    @RequestMapping(value = "/listTotalStats", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryTotalStats(){
        return CommonResult.success(cusCTRBService.getTotalStats());
    }

    @RequiredPermission(code = "0401")
    @ApiOperation(value = "查询客户平均订单价值统计信息")
    @RequestMapping(value = "/listAovStats", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAovStats(){
        return CommonResult.success(cusCTRBService.getAovStats());
    }

    @RequiredPermission(code = "0402")
    @ApiOperation(value = "查询客户构成统计信息")
    @RequestMapping(value = "/listConsistStats", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryConsistStats(){
        return CommonResult.success(cusCTRBService.getConsistStats());
    }

    @RequiredPermission(code = "0402")
    @ApiOperation(value = "查询客户满意度统计信息")
    @RequestMapping(value = "/listCusCsrStats", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryCusCsrStats(){
        return CommonResult.success(cusCTRBService.getCsrStats());
    }

    @RequiredPermission(code = "0402")
    @ApiOperation(value = "查询客户信用度统计信息")
    @RequestMapping(value = "/listCusCcrStats", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryCusCcrStats(){
        return CommonResult.success(cusCTRBService.getCcrStats());
    }

    @RequiredPermission(code = "0403")
    @ApiOperation(value = "查询服务类型统计信息")
    @RequestMapping(value = "/listServiceTypeStats", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryServiceStats(){
        return CommonResult.success(serviceCTRBService.getServiceTypeStats());
    }

    @RequiredPermission(code = "0403")
    @ApiOperation(value = "查询服务满意度统计信息")
    @RequestMapping(value = "/listCsrStats", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryCsrStats(){
        return CommonResult.success(serviceCTRBService.getCsrStats());
    }
}
