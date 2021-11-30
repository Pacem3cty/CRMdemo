package com.example.crmdemo.modules.customer.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.customer.model.TCusOrder;
import com.example.crmdemo.modules.customer.model.TOrderDetails;
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
public interface TOrderDetailsService extends IService<TOrderDetails> {

    IPage findAll(Page page, Wrapper<TOrderDetails> queryWrapper);

    List<TOrderDetails> findAll(Wrapper<TOrderDetails> queryWrapper);

    List<Map<String, Object>> query(Wrapper<TOrderDetails> queryWrapper);

    boolean update(TOrderDetails tOrderDetails);

    boolean add(TOrderDetails tOrderDetails);

    boolean delete(String ids);

    boolean updateById(String ids);

    boolean updatePart(TOrderDetails tOrderDetails, Wrapper<TOrderDetails> updateWrapper);
}
