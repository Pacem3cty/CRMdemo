package com.example.crmdemo.modules.customer.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.customer.model.TCusReprieve;
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
public interface TCusReprieveService extends IService<TCusReprieve> {

    IPage findAll(Page page, Wrapper<TCusReprieve> queryWrapper);

    List<TCusReprieve> findAll(Wrapper<TCusReprieve> queryWrapper);

    List<Map<String, Object>> query(Wrapper<TCusReprieve> queryWrapper);

    boolean update(TCusReprieve tCusReprieve);

    boolean add(TCusReprieve tCusReprieve);

    boolean delete(String ids);

    boolean updateById(String ids);

    boolean updatePart(TCusReprieve tCusReprieve, Wrapper<TCusReprieve> updateWrapper);
}
