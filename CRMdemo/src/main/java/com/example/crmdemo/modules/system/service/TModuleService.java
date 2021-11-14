package com.example.crmdemo.modules.system.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.system.model.TModule;
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
public interface TModuleService extends IService<TModule> {

    IPage findAll(Page page, Wrapper<TModule> queryWrapper);

    List<Map<String, Object>> query(Wrapper<TModule> queryWrapper);

    List<TModule> findAll(Wrapper<TModule> queryWrapper);

    boolean update(TModule tModule);

    boolean add(TModule tModule);

    boolean delete(String ids);

    boolean updateById(String ids);

}
