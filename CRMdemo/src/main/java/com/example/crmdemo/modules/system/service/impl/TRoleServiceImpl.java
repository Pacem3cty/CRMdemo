package com.example.crmdemo.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.system.model.TRole;
import com.example.crmdemo.modules.system.mapper.TRoleMapper;
import com.example.crmdemo.modules.system.model.TUser;
import com.example.crmdemo.modules.system.service.TRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wr
 * @since 2021-10-09
 */
@Service
public class TRoleServiceImpl extends ServiceImpl<TRoleMapper, TRole> implements TRoleService {

    @Resource
    private TRoleMapper tRoleMapper;

    @Override
    public IPage findAll(Page page, Wrapper<TRole> queryWrapper) {
        IPage iPage = tRoleMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    @Override
    public List<TRole> findAll(Wrapper<TRole> queryWrapper) {
        return tRoleMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> query(Wrapper<TRole> queryWrapper) {
        return tRoleMapper.selectMaps(queryWrapper);
    }

    @Override
    public boolean update(TRole tRole) {
        tRole.setUpdateDate(new Date());
        int n = tRoleMapper.updateById(tRole);
        if (n == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean add(TRole tRole) {
        tRole.setCreateDate(new Date());
        int n = tRoleMapper.insert(tRole);
        if (n == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String ids) {
        AtomicBoolean flg = new AtomicBoolean(true);
        String[] array = ids.split(",");
        Arrays.stream(array).forEach(m -> {
            TRole tRole = tRoleMapper.selectById(m);
            int n = tRoleMapper.deleteById(tRole.getId());
            if (n == 0) {
                flg.set(false);
            }
        });
        return flg.get();
    }

    @Override
    public boolean updateById(String ids) {
        AtomicBoolean flg = new AtomicBoolean(true);
        String[] array = ids.split(",");
        Arrays.stream(array).forEach(m -> {
            TRole tRole = tRoleMapper.selectById(m);
            tRole.setIsValid(1);
            int n = tRoleMapper.updateById(tRole);
            if (n == 0) {
                flg.set(false);
            }
        });
        return flg.get();
    }
}
