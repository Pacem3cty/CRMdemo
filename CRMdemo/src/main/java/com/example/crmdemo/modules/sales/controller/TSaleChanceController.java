package com.example.crmdemo.modules.sales.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.sales.dto.TSaleChanceDto;
import com.example.crmdemo.modules.sales.model.TSaleChance;
import com.example.crmdemo.modules.sales.service.TSaleChanceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wr
 * @since 2021-10-20
 */
@RestController
@RequestMapping("/sales")
public class TSaleChanceController {

    @Autowired
    private TSaleChanceService tSaleChanceService;

    @ApiOperation(value = "查询营销机会信息")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll(@RequestBody TSaleChanceDto tSaleChanceDto) {
        QueryWrapper<TSaleChance> queryWrapper = new QueryWrapper<>();

        //客户名
        if(tSaleChanceDto.getCustomerName() != null && !tSaleChanceDto.getCustomerName().equals("")){
            queryWrapper.like("customer_name",tSaleChanceDto.getCustomerName());

        }
        //创建人
        if(tSaleChanceDto.getCreatePerson() != null && !tSaleChanceDto.getCreatePerson().equals("")){
            queryWrapper.like("create_person",tSaleChanceDto.getCreatePerson());

        }
        //分配状态
        if(tSaleChanceDto.getState() != null && !tSaleChanceDto.getState().equals("")){
            queryWrapper.eq("state",tSaleChanceDto.getState());
        }

        //建档日
        if(tSaleChanceDto.getStartTime() != null && !tSaleChanceDto.getStartTime().equals("")) {
            //格式化时间
            queryWrapper.ge("create_date", tSaleChanceDto.getStartTime());
            queryWrapper.le("create_date", tSaleChanceDto.getEndTime());
        }
        queryWrapper.eq("is_valid",0); //查询没有被软删除的记录
        queryWrapper.orderByAsc("id");//按升序排序

        Page page = new Page();//分页查询page类
        page.setCurrent(tSaleChanceDto.getCurrent());//获取当前页数
        page.setSize(tSaleChanceDto.getPageSize());//获取每页显示条目
        IPage<TSaleChance> pageResult = tSaleChanceService.findAll(page,queryWrapper);
        return CommonResult.success(pageResult);
    }

    //预留方法位置

    @ApiOperation(value = "物理删除营销机会信息")
    @RequestMapping(value = "/deletePhysical", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> deleteWl(@RequestBody String ids) {
        JSONObject object = JSONObject.parseObject(ids);
        ids = object.get("ids").toString();
        return CommonResult.success(tSaleChanceService.delete(ids));
    }


    @ApiOperation(value = "伪删除营销机会信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@RequestBody String ids) {
        JSONObject object = JSONObject.parseObject(ids);
        ids = object.get("ids").toString();
        return CommonResult.success(tSaleChanceService.updateById(ids));
    }
}

