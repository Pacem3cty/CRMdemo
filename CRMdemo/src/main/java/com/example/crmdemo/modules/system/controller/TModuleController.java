package com.example.crmdemo.modules.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.system.model.TModule;
import com.example.crmdemo.modules.system.model.TreeControlPojo;
import com.example.crmdemo.modules.system.model.TreeTablePojo;
import com.example.crmdemo.modules.system.service.TModuleService;
import com.example.crmdemo.utils.TreeUtil;
import com.example.crmdemo.utils.TypeConvertUtil;
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

    @ApiOperation(value = "查询所有资源信息（加载前端树形表格）")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll() throws StackOverflowError{//抛出在将空对象转换为JSON格式数据时出现无限递归调用的异常
        QueryWrapper<TModule> queryWrapper = new QueryWrapper<>();

        queryWrapper.select("id,parent_id as parentId,module_name as moduleName,url,opt_value as optValue");
        queryWrapper.eq("is_valid", 0);
        //注意查询字段应分别设置与树形数据类相同的别名 且类型必须为String 否则将导致后续List<Map<String, Object>>转换为List<TreeControlPojo>转换失败
        List<Map<String,Object>> mapList = tModuleService.query(queryWrapper);

        TreeTablePojo treeTablePojo = new TreeTablePojo();
        Class clazz = treeTablePojo.getClass();
        List<TreeTablePojo> treeTablePojoList = TypeConvertUtil.setList(mapList,clazz);

        /* getTreeList方法参数 参数一为顶层节点 pid为-1的节点定义为顶层节点 参数二为需要转换为树形数据链表的List<TreeTablePojo> */
        List<TreeTablePojo> treeTableList = TreeUtil.getTreeTable("-1",treeTablePojoList);

        return CommonResult.success(treeTableList);
    }

    @ApiOperation(value = "查询所有资源信息（加载前端树形控件）")
    @RequestMapping(value = "/loadData", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult loadData() throws StackOverflowError{//抛出空对象转换为JSON格式数据时无限递归调用的异常
//        List<TreeControlPojo> resultList = new ArrayList<>();

        QueryWrapper<TModule> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,parent_id as pid ,module_name as label").eq("is_valid", 0);
        //注意查询字段应分别设置与树形数据类相同的别名 且类型必须为String 否则将导致后续List<Map<String, Object>>转换为List<TreeControlPojo>转换失败
        List<Map<String, Object>> mapList = tModuleService.query(queryWrapper);

        TreeControlPojo treeControlPojo = new TreeControlPojo();
        Class clazz = treeControlPojo.getClass();//反射获取树形数据类对象
        List<TreeControlPojo> treeControlPojoList = TypeConvertUtil.setList(mapList,clazz);//将List<Map<String, Object>>转换为List<TreeControlPojo>

        /* getTreeList方法参数 参数一为顶层节点 pid为-1的节点定义为顶层节点 参数二为需要转换为树形数据链表的List<TreeControlPojo> */
        List<TreeControlPojo> childList = TreeUtil.getTreeList("-1", treeControlPojoList);

//        treeControlPojo.setId("0");
//        treeControlPojo.setLabel("授权资源列表");
//        treeControlPojo.setChildList(childList);

//        resultList.add(treeControlPojo);//已弃用 启用后前端选择授权角色资源时中将包含最顶层节点的模块编号0 导致授权时在模块表中无法查找到相应权限码抛出空指针异常

        return CommonResult.success(childList);
    }

    @ApiOperation(value = "新增资源信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> add(@RequestBody TModule tModule) {
        return CommonResult.success(tModuleService.add(tModule));
    }

    @ApiOperation(value = "修改资源信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@RequestBody TModule tModule) {
        return CommonResult.success(tModuleService.update(tModule));
    }

    @ApiOperation(value = "软删除资源信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@RequestBody String ids) {
        JSONObject object = JSONObject.parseObject(ids);
        ids = object.get("ids").toString();
        return CommonResult.success(tModuleService.updateById(ids));
    }

    @ApiOperation(value = "获取新增资源编号")
    @RequestMapping(value = "/getCurrentId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCurrentId() {
        Integer currentId;
        QueryWrapper<TModule> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MAX(id) as id");
        List<TModule> tModuleList = tModuleService.findAll(queryWrapper);
        if (tModuleList.get(0) == null) {
            currentId = 0;
        }
        else {
            currentId = tModuleList.get(0).getId();
        }
        currentId++;
        return CommonResult.success(currentId);
    }
}

