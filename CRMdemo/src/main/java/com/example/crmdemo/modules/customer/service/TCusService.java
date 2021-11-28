package com.example.crmdemo.modules.customer.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.customer.model.TCus;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wr
 * @since 2021-11-24
 */
public interface TCusService extends IService<TCus> {

    IPage findAll(Page page, Wrapper<TCus> queryWrapper);

    List<TCus> findAll(Wrapper<TCus> queryWrapper);

    List<Map<String, Object>> query(Wrapper<TCus> queryWrapper);

    boolean update(TCus tCus);

    boolean add(TCus tCus);

    boolean delete(String ids);

    boolean updateById(String ids);

    boolean updatePart(TCus tCus, Wrapper<TCus> updateWrapper);
}
