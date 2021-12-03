package com.example.crmdemo.modules.customer.mapper;

import com.example.crmdemo.modules.customer.model.TCus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wr
 * @since 2021-11-24
 */
public interface TCusMapper extends BaseMapper<TCus> {

    List<TCus> queryLossCus();

    Integer updateCusLossStatusByIds(List<Integer> cusIds);
}
