package com.example.crmdemo.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.system.model.TPermission;
import com.example.crmdemo.modules.system.mapper.TPermissionMapper;
import com.example.crmdemo.modules.system.service.TPermissionService;
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
 *  服务实现类
 * </p>
 *
 * @author wr
 * @since 2021-10-09
 */
@Service
public class TPermissionServiceImpl extends ServiceImpl<TPermissionMapper, TPermission> implements TPermissionService {

    @Resource
    private  TPermissionMapper tPermissionMapper;

    @Override
    public IPage findAll(Page page, Wrapper<TPermission> queryWrapper) {
        IPage iPage = tPermissionMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public List<Map<String, Object>> query(Wrapper<TPermission> queryWrapper) {
        return tPermissionMapper.selectMaps(queryWrapper);
    }

    @Override
    public List<TPermission> findAll(Wrapper<TPermission> queryWrapper) {
        return tPermissionMapper.selectList(queryWrapper);
    }

    @Override
    public Integer selectCount(Wrapper<TPermission> queryWrapper) {
        return tPermissionMapper.selectCount(queryWrapper);
    }

    @Override
    public TPermission selectOne(Wrapper<TPermission> queryWrapper) {
        return tPermissionMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean update(TPermission tPermission) {
        return false;
    }

    @Override
    public boolean add(TPermission tPermission) {
        tPermission.setCreateDate(new Date());
        int n = tPermissionMapper.insert(tPermission);
        if(n == 0 ){
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String ids) {
        AtomicBoolean flg= new AtomicBoolean(true);
        String[] array = ids.split(",");
        Arrays.stream(array).forEach(m -> {
            TPermission tPermission = tPermissionMapper.selectById(m);
            int n = tPermissionMapper.deleteById(tPermission.getId());
            if(n == 0 ){
                flg.set(false);
            }
        });
        return flg.get();
    }

    @Override
    public boolean updateById(String ids) {
        return false;
    }
}
