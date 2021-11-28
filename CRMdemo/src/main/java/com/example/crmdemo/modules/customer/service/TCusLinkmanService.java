package com.example.crmdemo.modules.customer.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.customer.model.TCusContact;
import com.example.crmdemo.modules.customer.model.TCusLinkman;
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
public interface TCusLinkmanService extends IService<TCusLinkman> {

    IPage findAll(Page page, Wrapper<TCusLinkman> queryWrapper);

    List<TCusLinkman> findAll(Wrapper<TCusLinkman> queryWrapper);

    List<Map<String, Object>> query(Wrapper<TCusLinkman> queryWrapper);

    boolean update(TCusLinkman tCusLinkman);

    boolean add(TCusLinkman tCusLinkman);

    boolean delete(String ids);

    boolean updateById(String ids);

    boolean updatePart(TCusLinkman tCusLinkman, Wrapper<TCusLinkman> updateWrapper);
}
