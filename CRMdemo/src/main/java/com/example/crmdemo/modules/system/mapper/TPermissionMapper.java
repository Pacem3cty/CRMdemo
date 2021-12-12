package com.example.crmdemo.modules.system.mapper;

import com.example.crmdemo.modules.system.model.TPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wr
 * @since 2021-10-09
 */
public interface TPermissionMapper extends BaseMapper<TPermission> {

    //通过角色编号查询权限记录数
    Integer countPermissionByRoleId(Integer roleId);


    //通过角色编号删除相应权限记录
    void deletePermissionByRoleId(Integer roleId);

}
