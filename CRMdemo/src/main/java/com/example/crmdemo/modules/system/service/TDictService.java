package com.example.crmdemo.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.system.model.TDict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.crmdemo.modules.system.model.TRole;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wr
 * @since 2022-01-07
 */
public interface TDictService extends IService<TDict> {

    IPage findAll(Page page, Wrapper<TDict> queryWrapper);

    List<TDict> findAll(Wrapper<TDict> queryWrapper);

    List<Map<String, Object>> query(Wrapper<TDict> queryWrapper);

    boolean update(TDict tDict);

    boolean add(TDict tDict);

    boolean delete(String ids);

    boolean updateById(String ids);
}
