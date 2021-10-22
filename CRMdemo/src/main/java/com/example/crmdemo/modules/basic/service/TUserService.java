package com.example.crmdemo.modules.basic.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.crmdemo.modules.basic.model.TUser;
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

     List<TUser> findAll(Wrapper<TUser> queryWrapper);

     boolean update(TUser tUser);
}
