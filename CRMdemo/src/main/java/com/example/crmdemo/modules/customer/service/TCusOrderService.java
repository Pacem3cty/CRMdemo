package com.example.crmdemo.modules.customer.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.customer.model.TCusOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wr
 * @since 2021-11-29
 */
public interface TCusOrderService extends IService<TCusOrder> {

    IPage findAll(Page page, Wrapper<TCusOrder> queryWrapper);

    List<TCusOrder> findAll(Wrapper<TCusOrder> queryWrapper);

    List<Map<String, Object>> query(Wrapper<TCusOrder> queryWrapper);

    boolean update(TCusOrder tCusOrder);

    boolean add(TCusOrder tCusOrder);

    boolean delete(String ids);

    boolean updateById(String ids);

    boolean updatePart(TCusOrder tCusOrder, Wrapper<TCusOrder> updateWrapper);

    List<TCusOrder> queryLastOrderDate(List<Integer> cusIds);
}
