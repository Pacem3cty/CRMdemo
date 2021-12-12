package com.example.crmdemo.modules.service.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.service.model.TCusService;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wr
 * @since 2021-12-03
 */
public interface TCusServiceService extends IService<TCusService> {

    /**
     * 查询服务信息 分页查询
     */
    IPage findAll(Page page, Wrapper<TCusService> queryWrapper);

    /**
     * 查询服务信息 查询部分字段
     */
    List<Map<String, Object>> query(Wrapper<TCusService> queryWrapper);

    /**
     * 新增服务信息
     */
    boolean add(TCusService tSaleChance);

    /**
     * 修改服务信息
     */
    boolean update(TCusService tSaleChance);

    /**
     * 删除服务信息(物理删除)
     */
    boolean delete(String ids);

    /**
     * 删除服务信息(软删除)
     */
    boolean updateById(String ids);

    /**
     * 查询服务信息 单页查询
     */
    List<TCusService> findAll(Wrapper<TCusService> queryWrapper);


    boolean updatePart(TCusService tSaleChance, Wrapper<TCusService> updateWrapper);
}
