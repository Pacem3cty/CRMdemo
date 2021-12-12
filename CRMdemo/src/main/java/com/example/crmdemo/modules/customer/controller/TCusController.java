package com.example.crmdemo.modules.customer.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.customer.dto.TCusDTO;
import com.example.crmdemo.modules.customer.model.TCus;
import com.example.crmdemo.modules.customer.model.TCusLoss;
import com.example.crmdemo.modules.customer.model.TCusOrder;
import com.example.crmdemo.modules.customer.service.TCusLossService;
import com.example.crmdemo.modules.customer.service.TCusOrderService;
import com.example.crmdemo.modules.customer.service.TCusService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wr
 * @since 2021-11-24
 */
@RestController
@RequestMapping("/cus")
public class TCusController {
    Integer currentId;

    private TCusService tCusService;

    private TCusOrderService tCusOrderService;

    private TCusLossService tCusLossService;

    @Autowired
    public void settCusService(TCusService tCusService) {
        this.tCusService = tCusService;
    }

    @Autowired
    public void settCusLossService(TCusLossService tCusLossService) {
        this.tCusLossService = tCusLossService;
    }

    @Autowired
    public void settCusOrderService(TCusOrderService tCusOrderService) {
        this.tCusOrderService = tCusOrderService;
    }



    @ApiOperation(value = "查询客户信息")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll(@RequestBody TCusDTO tCusDTO) {
        QueryWrapper<TCus> queryWrapper = new QueryWrapper<>();

        //客户名称
        if (tCusDTO.getCusName() != null && !tCusDTO.getCusName().equals("")) {
            queryWrapper.like("cus_name", tCusDTO.getCusName());
        }

        //客户编号
        if (tCusDTO.getCusNum() != null && !tCusDTO.getCusNum().equals("")) {
            queryWrapper.eq("cus_num", tCusDTO.getCusNum());
        }

        queryWrapper.eq("is_valid", 0); //查询没有被软删除的记录
        queryWrapper.orderByAsc("id");//按升序排序

        Page page = new Page();//分页查询page类
        page.setCurrent(tCusDTO.getCurrent());//获取当前页
        page.setSize(tCusDTO.getPageSize());//获取每页显示条目
        IPage<TCus> pageResult = tCusService.findAll(page, queryWrapper);
        return CommonResult.success(pageResult);
    }

    @ApiOperation(value = "新增客户信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> add(@RequestBody TCus tCus) {
        return CommonResult.success(tCusService.add(tCus));
    }

    @ApiOperation(value = "修改客户信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@RequestBody TCus tCus) {
        return CommonResult.success(tCusService.update(tCus));
    }

    @ApiOperation(value = "软删除客户信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@RequestBody String ids) {
        JSONObject object = JSONObject.parseObject(ids);
        ids = object.get("ids").toString();
        return CommonResult.success(tCusService.updateById(ids));
    }

    @ApiOperation(value = "获取新增客户序号")
    @RequestMapping(value = "/getCurrentId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCurrentId() {
        QueryWrapper<TCus> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MAX(id) as id");
        List<TCus> tCusList = tCusService.findAll(queryWrapper);
        if (tCusList.get(0) == null) {
            currentId = 0;
        } else {
            currentId = tCusList.get(0).getId();
        }
        currentId++;
        return CommonResult.success(currentId);
    }

    @ApiOperation(value = "获取新增客户编号")
    @RequestMapping(value = "/getCusNum", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCusNum() {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        QueryWrapper<TCus> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MAX(id) as id");
        List<TCus> tCusList = tCusService.findAll(queryWrapper);
        if (tCusList.get(0) == null) {
            currentId = 0;
        } else {
            currentId = tCusList.get(0).getId();
        }
        currentId++;
        String cusNum = "C" + date + String.format("%07d", currentId);//生成由日期+前位补0的7位序号码组成的客户编号
        return CommonResult.success(cusNum);
    }

    @ApiOperation(value = "查询客户名称信息")
    @RequestMapping(value = "/listCusName", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult listCusName() {
        QueryWrapper<TCus> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("cus_num as cusNum,cus_name as cusName").eq("is_valid", 0).groupBy("cus_name");//GROUP BY 较 DISTINCT 性能更优
        List<Map<String, Object>> tCusList = tCusService.query(queryWrapper);
        return CommonResult.success(tCusList);
    }

    @ApiOperation(value = "更新客户流失及状态")
    @Scheduled(cron = "* * 9 * * ?")//设定每天9时执行
    @RequestMapping(value = "/updateCusState", method = RequestMethod.POST)
//    @ResponseBody
    public void updateCusState() {
        System.err.println("=====开始更新客户流失及状态=====");

        List<TCus> lossCusList = tCusService.queryLossCus();//查询待流失客户
        List<Integer> lossCusIds = new ArrayList<>();//待流失客户序号集合

        if (lossCusList != null && lossCusList.size() > 0) {//判断是否存在待流失客户
            List<TCusLoss> tCusLossList = new ArrayList<>();//定义流失客户列表
            lossCusList.forEach(lossCus -> {
                lossCusIds.add(lossCus.getId());//添加待流失客户编号
            });

            Map<Integer, Date> lastOrderDateMap = new HashMap<>();
            List<TCusOrder> tCusOrderList = tCusOrderService.queryLastOrderDate(lossCusIds);//批量查询待流失客户最后下单时间

            for (TCusOrder tCusOrder : tCusOrderList) {//遍历List 并放入相应Map中 Map中键为客户编号 值为最后下单时间
                lastOrderDateMap.put(tCusOrder.getCusId(), tCusOrder.getOrderDate());
            }

            //遍历待流失客户数据
            lossCusList.forEach(lossCus -> {
//                QueryWrapper<TCusOrder> queryWrapper = new QueryWrapper<>();

                TCusLoss tCusLoss = new TCusLoss();//定义流失客户对象

                tCusLoss.setCreateDate(new Date());//建档日期
                tCusLoss.setUpdateDate(new Date());//修改日期
                tCusLoss.setCusManager(lossCus.getCusManager());//客户经理
                tCusLoss.setCusName(lossCus.getCusName());//客户名称
                tCusLoss.setCusNum(lossCus.getCusNum());//客户编号
                tCusLoss.setLastOrderTime(lastOrderDateMap.get(lossCus.getId()));//从Map中获取最后下单时间
                tCusLoss.setIsValid(0);//状态为有效
                tCusLoss.setState(0);//状态为暂缓流失

//                queryWrapper.eq("is_valid", 0).eq("cus_id", lossCus.getId());//查询指定客户的最后一条订单记录
//                queryWrapper.orderByDesc("order_date").last("limit 1");
//                TCusOrder tCusOrder = tCusOrderService.getOne(queryWrapper);//已弃用并重构优化 将查询数据库操作统一移至遍历外层以提高查询性能

//                if (tCusOrder != null) {
//                    tCusLoss.setLastOrderTime(tCusOrder.getOrderDate());
//                }

                tCusLossList.add(tCusLoss);

            });

            if (tCusLossService.saveBatch(tCusLossList)) {//批量添加流失客户记录 数据源已配置SQL批处理提升批量插入性能
                tCusService.updateCusLossStatusByIds(lossCusIds);
            }
            else {
                System.err.println("=====批量添加流失客户记录失败=====");
            }

            System.err.println("=====更新客户流失及状态成功=====");
        }
        else {
            System.err.println("=====查询到当前无待流失客户 更新结束=====");
        }
    }

}


