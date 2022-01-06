package com.example.crmdemo.modules.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.system.dto.TUserDTO;
import com.example.crmdemo.modules.system.model.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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

    List<Map<String,Object>> pageListUser(Page page,@Param("tUserDTO") TUserDTO tUserDTO);
}
