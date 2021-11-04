package com.example.crmdemo.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.system.model.TRole;
import com.example.crmdemo.modules.system.model.TUserRole;
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
public interface TUserRoleService extends IService<TUserRole> {
    IPage findAll(Page page, Wrapper<TUserRole> queryWrapper);

    List<TUserRole> findAll(Wrapper<TUserRole> queryWrapper);

    boolean update(TUserRole tUserRole);

    boolean add(TUserRole tUserRole);

    boolean delete(String ids);

    boolean updateById(String ids);
}
