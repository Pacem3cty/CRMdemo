package com.example.crmdemo.modules.system.mapper;

import com.example.crmdemo.modules.system.model.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wr
 * @since 2021-10-09
 */
public interface TUserMapper extends BaseMapper<TUser> {

    List<Map<String,Object>> listUserRoleName();
}
