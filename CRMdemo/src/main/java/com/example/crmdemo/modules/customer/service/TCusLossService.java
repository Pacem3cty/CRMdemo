package com.example.crmdemo.modules.customer.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.customer.model.TCusLoss;
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
public interface TCusLossService extends IService<TCusLoss> {

    IPage findAll(Page page, Wrapper<TCusLoss> queryWrapper);

    List<TCusLoss> findAll(Wrapper<TCusLoss> queryWrapper);

    List<Map<String, Object>> query(Wrapper<TCusLoss> queryWrapper);

    boolean update(TCusLoss tCusLoss);

    boolean add(TCusLoss tCusLoss);

    boolean delete(String ids);

    boolean updateById(String ids);

    boolean updatePart(TCusLoss tCusLoss, Wrapper<TCusLoss> updateWrapper);

    TCusLoss selectOne(Wrapper<TCusLoss> queryWrapper);
}
