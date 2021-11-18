package com.example.crmdemo.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.system.model.TModule;
import com.example.crmdemo.modules.system.model.TPermission;
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
public interface TPermissionService extends IService<TPermission> {

    IPage findAll(Page page, Wrapper<TPermission> queryWrapper);

    List<Map<String, Object>> query(Wrapper<TPermission> queryWrapper);

    List<TPermission> findAll(Wrapper<TPermission> queryWrapper);

    Integer selectCount(Wrapper<TPermission> queryWrapper);

    TPermission selectOne(Wrapper<TPermission> queryWrapper);

    boolean update(TPermission tPermission);

    boolean add(TPermission tPermission);

    boolean delete(String ids);

    boolean updateById(String ids);
}
