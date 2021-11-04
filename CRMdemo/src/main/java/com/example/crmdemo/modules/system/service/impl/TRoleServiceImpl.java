package com.example.crmdemo.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.system.model.TRole;
import com.example.crmdemo.modules.system.mapper.TRoleMapper;
import com.example.crmdemo.modules.system.service.TRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wr
 * @since 2021-10-09
 */
@Service
public class TRoleServiceImpl extends ServiceImpl<TRoleMapper, TRole> implements TRoleService {

    @Resource
    private  TRoleMapper tRoleMapper;

    @Override
    public IPage findAll(Page page, Wrapper<TRole> queryWrapper) {
        IPage iPage = tRoleMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public List<TRole> findAll(Wrapper<TRole> queryWrapper) {
        return tRoleMapper.selectList(queryWrapper);
    }

    @Override
    public boolean update(TRole tRole) {
        return false;
    }

    @Override
    public boolean add(TRole tRole) {
        return false;
    }

    @Override
    public boolean delete(String ids) {
        return false;
    }

    @Override
    public boolean updateById(String ids) {
        return false;
    }
}
