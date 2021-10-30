package com.example.crmdemo.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.sales.model.TCusDevPlan;
import com.example.crmdemo.modules.system.model.TUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wr
 * @since 2021-10-09
 */
public interface TUserService extends IService<TUser> {

     IPage findAll(Page page, Wrapper<TUser> queryWrapper);

     List<TUser> findAll(Wrapper<TUser> queryWrapper);

     boolean update(TUser tUser);
}
