package com.example.crmdemo.modules.basic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.basic.model.Info;
import com.example.crmdemo.modules.basic.model.Login;
import com.example.crmdemo.modules.basic.model.LoginInfo;
import com.example.crmdemo.modules.basic.model.TUser;
import com.example.crmdemo.modules.basic.service.TUserService;
import com.example.crmdemo.util.AntiSQLInjectionUtil;
import com.example.crmdemo.util.Md5Util;
import com.example.crmdemo.util.TokenUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wr
 * @since 2021-10-09
 */
@RestController
@RequestMapping("")
public class TUserController {

    @Autowired
    private TUserService tUserService;

    private String dbPwd = "";
    private String userName = "";
    private String userPwd = "";
    private String newPwd = "";
    private Integer id;
    private final TUser tUser = new TUser();
    Info info = new Info();
    List<TUser> tUserNameList = null;
    private Integer result = 0;//修改成功 0 | 无效账号 1 | 原密码错误 2 | 修改失败 3
    QueryWrapper<TUser> queryWrapper = new QueryWrapper<>();

    @ApiOperation(value= "获取用户资料")
    @RequestMapping(value = "/user/listInfo",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult listUserInfo(@RequestBody Object id){

        if(id != null && !id.equals("")) {
            queryWrapper.eq("id",id).eq("is_valid", 0);//查询数据库是否含存在相同用户名且有效的用户信息记录
        }
        tUserNameList = tUserService.findAll(queryWrapper);
        if(tUserNameList != null && !tUserNameList.isEmpty() ){
            info.setId(tUserNameList.get(0).getId());
            info.setUserName(tUserNameList.get(0).getUserName());
            info.setTrueName(tUserNameList.get(0).getTrueName());
            info.setPhone(tUserNameList.get(0).getPhone());
            info.setEmail(tUserNameList.get(0).getEmail());
            info.setRemark(tUserNameList.get(0).getRemark());
        }
        return CommonResult.success(info);
        }

    @ApiOperation(value= "用戶登錄")
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult userLogin(@RequestBody Login login){

        userName = login.getUserName();
        userPwd = login.getUserPwd();

//        System.out.println(userName);
//        String dbPwd = "";
        String trueName =  "";
        String token = "";
        String loginTip = "";//提示消息

        //防止SQL注入
        AntiSQLInjectionUtil.SQL(userName);
        AntiSQLInjectionUtil.SQL(userPwd);

        if(userName != null && !userName.equals("")) {
            queryWrapper.eq("user_name", userName).eq("is_valid", 0);//查询数据库是否含存在相同用户名且有效的用户信息记录
        }
        tUserNameList = tUserService.findAll(queryWrapper);
        if(tUserNameList != null && !tUserNameList.isEmpty()){
            dbPwd = tUserNameList.get(0).getUserPwd();//获取数据库中存储的密码
            trueName = tUserNameList.get(0).getTrueName();
            id = tUserNameList.get(0).getId();
        }
        if(!Md5Util.passwordIsTrue(userPwd, dbPwd)){
            loginTip = "用户名称不存在或密码错误！";//登录错误时对返回提示进行模糊处理
            trueName = "";//防止在登录错误时暴露真实姓名
        }
        else{
            loginTip = "登录成功！";
            token =TokenUtils.sign(userName,userPwd);
        }
        //（待定）登录信息对象将返回给前端 包含：对前端传输到后台的密码进行MD5处理并与数据库中相应记录的密码比对结果、登录用户名、根据登录名和密码生成Token秘钥
        LoginInfo loginInfo = new LoginInfo(Md5Util.passwordIsTrue(userPwd, dbPwd),id,userName,trueName,token,loginTip);

        return CommonResult.success(loginInfo);
    }
    @ApiOperation(value= "更改密码")
    @RequestMapping(value = "/user/updatePwd",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePwd(@RequestBody Login login) {

        userName = login.getUserName();
        userPwd = login.getUserPwd();
        newPwd = login.getNewPwd();

        //防止SQL注入
        AntiSQLInjectionUtil.SQL(userName);
        AntiSQLInjectionUtil.SQL(userPwd);

        if (userName != null && !userName.equals("")) {
            queryWrapper.eq("user_name", userName).eq("is_valid", 0);//查询数据库是否含存在相同用户名且有效的用户信息记录
        }
        tUserNameList = tUserService.findAll(queryWrapper);
        if (tUserNameList != null && !tUserNameList.isEmpty()) {
            dbPwd = tUserNameList.get(0).getUserPwd();//获取数据库中存储的密码

        }
        else{
            result = 1;
        }
        if (!Md5Util.passwordIsTrue(userPwd, dbPwd)) {
            result = 2;
        }
        else {
            tUser.setId(tUserNameList.get(0).getId());
            tUser.setUserName(userName);
            tUser.setUserPwd(Md5Util.string2MD5(newPwd));
            tUser.setTrueName(tUserNameList.get(0).getTrueName());
            tUser.setEmail(tUserNameList.get(0).getEmail());
            tUser.setPhone(tUserNameList.get(0).getPhone());
            tUser.setRemark(tUserNameList.get(0).getRemark());
            tUser.setIsValid(tUserNameList.get(0).getIsValid());
            tUser.setCreateDate(tUserNameList.get(0).getCreateDate());
            tUser.setUpdateDate(tUserNameList.get(0).getUpdateDate());
        }
        if(!tUserService.update(tUser)){
            result = 3;
        }
        return CommonResult.success(result);
    }

    @ApiOperation(value= "更改资料")
    @RequestMapping(value = "/user/updateInfo",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateInfo(@RequestBody Info info) {
        userName = info.getUserName();
        //防止SQL注入
        AntiSQLInjectionUtil.SQL(userName);

        if (userName != null && !userName.equals("")) {
            queryWrapper.eq("user_name", userName).eq("is_valid", 0);//查询数据库是否含存在相同用户名且有效的用户信息记录
        }
       tUserNameList = tUserService.findAll(queryWrapper);
        if (tUserNameList == null && tUserNameList.isEmpty()) {
            result = 1;
        }
            tUser.setId(tUserNameList.get(0).getId());
            tUser.setUserName(userName);
            tUser.setUserPwd(tUserNameList.get(0).getUserPwd());
            tUser.setTrueName(tUserNameList.get(0).getTrueName());
            tUser.setEmail(info.getEmail());
            tUser.setPhone(info.getPhone());
            tUser.setRemark(info.getRemark());
            tUser.setIsValid(tUserNameList.get(0).getIsValid());
            tUser.setCreateDate(tUserNameList.get(0).getCreateDate());
            tUser.setUpdateDate(tUserNameList.get(0).getUpdateDate());

        if(!tUserService.update(tUser)){
            result = 3;
        }
        return CommonResult.success(result);
    }
}

