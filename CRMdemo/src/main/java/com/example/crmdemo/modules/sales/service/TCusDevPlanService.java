package com.example.crmdemo.modules.sales.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.sales.model.TCusDevPlan;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wr
 * @since 2021-10-20
 */
public interface TCusDevPlanService extends IService<TCusDevPlan> {
    /**
     * 查询营销机会信息 分页查询
     */
    IPage findAll(Page page, Wrapper<TCusDevPlan> queryWrapper);

    /**
     * 新增营销机会信息
     */
    boolean add(TCusDevPlan tSaleChance);

    /**
     * 修改营销机会信息
     */
    boolean update(TCusDevPlan tSaleChance);

    /**
     * 删除营销机会信息(物理删除)
     */
    boolean delete(String ids);

    /**
     * 删除营销机会信息(软删除)
     */
    boolean updateById(String ids);

    /**
     * 查询营销机会信息 单页查询
     */
    List<TCusDevPlan> findAll(Wrapper<TCusDevPlan> queryWrapper);
}
