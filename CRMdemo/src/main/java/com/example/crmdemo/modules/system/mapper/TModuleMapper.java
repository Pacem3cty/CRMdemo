package com.example.crmdemo.modules.system.mapper;

import com.example.crmdemo.modules.system.model.TModule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wr
 * @since 2021-10-09
 */
public interface TModuleMapper extends BaseMapper<TModule> {

    List<TModule> queryOptValueByIds(Integer[] array);

}
