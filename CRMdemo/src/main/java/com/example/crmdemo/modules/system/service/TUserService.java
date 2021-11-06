package com.example.crmdemo.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.system.model.TUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

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

     List<Map<String, Object>> query(Wrapper<TUser> queryWrapper);

     boolean update(TUser tUser);

     boolean add(TUser tUser);

     boolean delete(String ids);

     boolean updateById(String ids);

     boolean updatePart(TUser tUser, Wrapper<TUser> updateWrapper);
}
