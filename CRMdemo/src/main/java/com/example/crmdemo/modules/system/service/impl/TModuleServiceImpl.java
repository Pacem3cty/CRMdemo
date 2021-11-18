package com.example.crmdemo.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.system.model.TModule;
import com.example.crmdemo.modules.system.mapper.TModuleMapper;
import com.example.crmdemo.modules.system.model.TPermission;
import com.example.crmdemo.modules.system.service.TModuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wr
 * @since 2021-10-09
 */
@Service
public class TModuleServiceImpl extends ServiceImpl<TModuleMapper, TModule> implements TModuleService {

    @Resource
    private  TModuleMapper tModuleMapper;

    @Override
    public IPage findAll(Page page, Wrapper<TModule> queryWrapper) {
        IPage iPage = tModuleMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public List<Map<String, Object>> query(Wrapper<TModule> queryWrapper) {
        return tModuleMapper.selectMaps(queryWrapper);
    }

    @Override
    public List<TModule> findAll(Wrapper<TModule> queryWrapper) {
        return tModuleMapper.selectList(queryWrapper);
    }

    @Override
    public TModule selectOne(Wrapper<TModule> queryWrapper) {
        return tModuleMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean update(TModule tModule) {
        return false;
    }

    @Override
    public boolean add(TModule tModule) {
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
