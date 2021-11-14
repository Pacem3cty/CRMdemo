package com.example.crmdemo.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.system.model.TModule;
import com.example.crmdemo.modules.system.model.TModuleTree;
import com.example.crmdemo.modules.system.service.TModuleService;
import com.example.crmdemo.util.Tree;
import com.example.crmdemo.util.TreeUtil;
import com.example.crmdemo.util.TypeConvertUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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

    @ApiOperation(value = "查询所有资源信息")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll() {
        QueryWrapper<TModule> queryWrapper = new QueryWrapper<>();

        queryWrapper.select("id,parent_id as pid,module_name as name").eq("is_valid", 0);
//        queryWrapper.orderByAsc("id");//按升序排序
        List<Map<String, Object>> zTreeList = tModuleService.query(queryWrapper);
        return CommonResult.success(zTreeList);
    }

    @ApiOperation(value = "查询所有资源信息（加载树形控件）")
    @RequestMapping(value = "/loadData", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult loadData() throws StackOverflowError{//抛出JSON将对象转换为JSON格式数据时出现无限递归调用的异常
        List<TModuleTree> resultList = new ArrayList<>();

        QueryWrapper<TModule> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,parent_id as pid ,module_name as label").eq("is_valid", 0);
        //注意查询字段应分别设置与树形数据类相同的别名 否则将导致后续List<Map<String, Object>>转换为List<TModuleTree>转换失败
        List<Map<String, Object>> mapList = tModuleService.query(queryWrapper);

        TModuleTree tModuleTree = new TModuleTree();
        Class clazz = tModuleTree.getClass();//反射获取树形数据类对象
        List<TModuleTree> tModuleTreeList = TypeConvertUtil.setList(mapList,clazz);//将List<Map<String, Object>>转换为List<TModuleTree>
//        System.err.println("转换后链表："+tModuleTreeList);

        /* getTreeList方法参数 参数一为顶层节点 pid为-1的节点定义为顶层节点 参数二为需要转换为树形数据链表的List<TModuleTree> */
        List<TModuleTree> childList = TreeUtil.getTreeList("-1",tModuleTreeList);//
        tModuleTree.setId("0");
        tModuleTree.setLabel("授权资源列表");
        tModuleTree.setChildList(childList);
//        System.err.println("叶子结点链表："+childList);

        resultList.add(tModuleTree);
//        System.err.println("树形数据链表："+resultList);
        return CommonResult.success(resultList);
    }
}

