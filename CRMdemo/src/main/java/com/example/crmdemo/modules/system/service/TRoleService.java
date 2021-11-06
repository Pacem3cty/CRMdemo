package com.example.crmdemo.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.system.model.TRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crmdemo.modules.system.model.TUser;

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
public interface TRoleService extends IService<TRole> {
    IPage findAll(Page page, Wrapper<TRole> queryWrapper);

    List<TRole> findAll(Wrapper<TRole> queryWrapper);

    List<Map<String, Object>> query(Wrapper<TRole> queryWrapper);

    boolean update(TRole tRole);

    boolean add(TRole tRole);

    boolean delete(String ids);

    boolean updateById(String ids);
}
