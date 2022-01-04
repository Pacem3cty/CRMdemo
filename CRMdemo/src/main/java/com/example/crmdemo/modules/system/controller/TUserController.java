package com.example.crmdemo.modules.system.controller;


import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.common.api.CommonResult;

import com.example.crmdemo.modules.system.dto.TUserDTO;
import com.example.crmdemo.modules.system.model.TUser;
import com.example.crmdemo.modules.system.service.TUserService;
import com.example.crmdemo.utils.AESUtil;
import com.example.crmdemo.utils.AntiSQLInjectionUtil;
import com.example.crmdemo.utils.Md5Util;
import com.example.crmdemo.utils.TokenUtil;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wr
 * @since 2021-10-09
 */
@RestController
@RequestMapping("/user")
public class TUserController {

    private TUserService tUserService;

    @Autowired
    public void setTUserService(TUserService tUserService) {
        this.tUserService = tUserService;
    }

    @ApiOperation(value = "获取用户资料")
    @RequestMapping(value = "/listInfo", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult listUserInfo(@RequestBody Object id) {
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();

        if (id != null && !id.equals("")) {
            queryWrapper.select("id,user_name as userName,true_name as trueName,phone,email,remark").eq("id", id).eq("is_valid", 0);
        }

        List<Map<String, Object>> tUserNameList = tUserService.query(queryWrapper);

        System.err.println(tUserNameList);//数据检查
        return CommonResult.success(tUserNameList);
    }

    @ApiOperation(value = "用戶登錄")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult userLogin(@RequestBody String jsonStr) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String userName = jsonObject.getString("userName");
        String userPwd = jsonObject.getString("userPwd");

        Integer id = 0;
        String dbPwd = "";
        String trueName = "";
        String token = "";
        String loginTip;//提示消息

        //防止SQL注入
        AntiSQLInjectionUtil.SQL(userName);
        AntiSQLInjectionUtil.SQL(userPwd);

        if (userName != null && !userName.equals("")) {
            queryWrapper.select("id,user_pwd,true_name").eq("user_name", userName).eq("is_valid", 0);//查询数据库是否含存在相同用户名且有效的用户信息记录
        }
        List<TUser> tUserNameList = tUserService.findAll(queryWrapper);
        if (tUserNameList != null && !tUserNameList.isEmpty()) {
            dbPwd = tUserNameList.get(0).getUserPwd();//获取数据库中存储的密码
            trueName = tUserNameList.get(0).getTrueName();
            id = tUserNameList.get(0).getId();
        }
        if (!Md5Util.passwordIsTrue(userPwd, dbPwd)) {
            loginTip = "用户名称不存在或密码错误！";//登录错误时对返回提示进行模糊处理
            trueName = "";//防止在登录错误时暴露真实姓名
        } else {
            loginTip = "登录成功！";
//            token = TokenUtil.sign(userName, userPwd);
            token = AESUtil.encrypt(id.toString());
        }

        List<Map<String, Object>> loginInfo = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        //返回给前端的登录反馈信息 包含：对前端传输到后台的密码进行MD5处理并与数据库中相应记录的密码比对结果、登录用户名、根据登录名和密码生成Token秘钥
        map.put("pwdIsTrue", Md5Util.passwordIsTrue(userPwd, dbPwd));
        map.put("id", id);
        map.put("userName", userName);
        map.put("trueName", trueName);
        map.put("token", token);
        map.put("loginTip", loginTip);
        loginInfo.add(map);

        System.err.println(loginInfo);//数据检查
        return CommonResult.success(loginInfo);
    }

    @ApiOperation(value = "更改密码")
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePwd(@RequestBody String jsonStr) {
        TUser tUser = new TUser();

        int result = 0;//修改成功 0 | 无效账号 1 | 原密码错误 2 | 修改失败 3

        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        UpdateWrapper<TUser> updateWrapper = new UpdateWrapper<>();

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String userName = jsonObject.getString("userName");
        String userPwd = jsonObject.getString("userPwd");
        String newPwd = jsonObject.getString("newPwd");

        String dbPwd = "";

        //防止SQL注入
        AntiSQLInjectionUtil.SQL(userName);
        AntiSQLInjectionUtil.SQL(userPwd);

        if (userName != null && !userName.equals("")) {
            queryWrapper.select("user_pwd").eq("user_name", userName).eq("is_valid", 0);//查询数据库相同用户名的有效用户信息记录的密码
        }
        List<TUser> tUserNameList = tUserService.findAll(queryWrapper);
        if (tUserNameList != null && !tUserNameList.isEmpty()) {
            dbPwd = tUserNameList.get(0).getUserPwd();//获取数据库中存储的密码

        } else {
            result = 1;
        }
        if (!Md5Util.passwordIsTrue(userPwd, dbPwd)) {
            result = 2;
        } else {
            updateWrapper.set("user_pwd", Md5Util.string32MD5(newPwd)).eq("user_name", userName).eq("is_valid", 0);
        }
        if (!tUserService.updatePart(tUser, updateWrapper)) {
            result = 3;
        }
        System.err.println("result = " + result);//数据检查
        return CommonResult.success(result);
    }

    @ApiOperation(value = "更改资料")
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateInfo(@RequestBody String jsonStr) {
        TUser tUser = new TUser();

        Integer result = 0;//修改成功 0 | 无效账号 1 | 原密码错误 2 | 修改失败 3

        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        UpdateWrapper<TUser> updateWrapper = new UpdateWrapper<>();

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String userName = jsonObject.getString("userName");

//        String userName = info.getUserName();
        //防止SQL注入
        AntiSQLInjectionUtil.SQL(userName);

        if (userName != null && !userName.equals("")) {
            queryWrapper.eq("user_name", userName).eq("is_valid", 0);//查询数据库是否含存在相同用户名且有效的用户信息记录

        }
        List<TUser> tUserNameList = tUserService.findAll(queryWrapper);
        if (tUserNameList == null && tUserNameList.isEmpty()) {
            result = 1;
        }
        updateWrapper.set("email", jsonObject.getString("email"));
        updateWrapper.set("phone", jsonObject.getString("phone"));
        updateWrapper.set("remark", jsonObject.getString("remark"));

        if (!tUserService.updatePart(tUser, updateWrapper)) {
            result = 3;
        }
        System.err.println(result);//数据检查
        return CommonResult.success(result);
    }

    @ApiOperation(value = "查询用户信息")
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryAll(@RequestBody TUserDTO tUserDTO) {
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();


        //用户名称
        if (tUserDTO.getUserName() != null && !tUserDTO.getUserName().equals("")) {
            queryWrapper.like("user_name", tUserDTO.getUserName());
        }

        //邮箱
        if (tUserDTO.getEmail() != null && !tUserDTO.getEmail().equals("")) {
            queryWrapper.eq("email", tUserDTO.getEmail());
        }

        //电话
        if (tUserDTO.getPhone() != null && !tUserDTO.getPhone().equals("")) {
            queryWrapper.eq("phone", tUserDTO.getPhone());
        }

        //真实姓名
        if (tUserDTO.getTrueName() != null && !tUserDTO.getTrueName().equals("")) {
            queryWrapper.like("true_name", tUserDTO.getTrueName());
        }

        //备注
        if (tUserDTO.getRemark() != null && !tUserDTO.getRemark().equals("")) {
            queryWrapper.like("remark", tUserDTO.getRemark());
        }

        queryWrapper.eq("is_valid", 0); //查询没有被软删除的记录
        queryWrapper.orderByAsc("id");//按升序排序

        Page page = new Page();//分页查询page类
        page.setCurrent(tUserDTO.getCurrent());//获取当前页
        page.setSize(tUserDTO.getPageSize());//获取每页显示条目
        IPage<TUser> pageResult = tUserService.findAll(page, queryWrapper);
        return CommonResult.success(pageResult);
    }

    @ApiOperation(value = "查询用户名称信息")
    @RequestMapping(value = "/listUserName", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult listUserName() {//不另提供筛选查询分配状态已分配的信息 避免冗余
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_name as userName").eq("is_valid", 0).groupBy("user_name");
        List<Map<String, Object>> tUserList = tUserService.query(queryWrapper);
        return CommonResult.success(tUserList);
    }

    @ApiOperation(value = "查询真实姓名信息")
    @RequestMapping(value = "/listTrueName", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult listTrueName() {
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("true_name as trueName").eq("is_valid", 0).groupBy("true_name");
        List<Map<String, Object>> tUserList = tUserService.query(queryWrapper);
        return CommonResult.success(tUserList);
    }

    @ApiOperation(value = "新增用户信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> add(@RequestBody TUser tUser) {
        return CommonResult.success(tUserService.add(tUser));
    }

    @ApiOperation(value = "修改用户信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> update(@RequestBody TUser tUser) {
        return CommonResult.success(tUserService.update(tUser));
    }

    @ApiOperation(value = "获取新增用户编号")
    @RequestMapping(value = "/getCurrentId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getCurrentId() {
        Integer currentId;
        QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("MAX(id) as id");
        List<TUser> tUserList = tUserService.findAll(queryWrapper);
        if (tUserList.get(0) == null) {
            currentId = 0;
        }
        else {
            currentId = tUserList.get(0).getId();
        }
        currentId++;
        return CommonResult.success(currentId);
    }

    @ApiOperation(value = "软删除营销机会信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Boolean> delete(@RequestBody String ids) {
        JSONObject object = JSONObject.parseObject(ids);
        ids = object.get("ids").toString();
        return CommonResult.success(tUserService.updateById(ids));
    }

    @ApiOperation(value = "查询用户角色信息")
    @RequestMapping(value = "/queryUserRole", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryUserRole() {
       List<Map<String,Object>> userRoleList = tUserService.queryUserRoleName();

        return CommonResult.success(userRoleList);
    }

}

