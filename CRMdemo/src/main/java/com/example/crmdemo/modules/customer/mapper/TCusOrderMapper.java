package com.example.crmdemo.modules.customer.mapper;

import com.example.crmdemo.modules.customer.model.TCusOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wr
 * @since 2021-11-29
 */
public interface TCusOrderMapper extends BaseMapper<TCusOrder> {

    List<TCusOrder> queryLastOrderDateByIds(List<Integer> cusIds);
}
