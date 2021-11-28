package com.example.crmdemo.modules.customer.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.crmdemo.modules.customer.model.TCusContact;
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
public interface TCusContactService extends IService<TCusContact> {

    IPage findAll(Page page, Wrapper<TCusContact> queryWrapper);

    List<TCusContact> findAll(Wrapper<TCusContact> queryWrapper);

    List<Map<String, Object>> query(Wrapper<TCusContact> queryWrapper);

    boolean update(TCusContact tCusContact);

    boolean add(TCusContact tCusContact);

    boolean delete(String ids);

    boolean updateById(String ids);

    boolean updatePart(TCusContact tCusContact, Wrapper<TCusContact> updateWrapper);
}
