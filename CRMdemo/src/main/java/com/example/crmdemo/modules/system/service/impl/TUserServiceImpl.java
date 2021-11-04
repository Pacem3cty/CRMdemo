package com.example.crmdemo.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.sales.model.TSaleChance;
import com.example.crmdemo.modules.system.model.TUser;
import com.example.crmdemo.modules.system.mapper.TUserMapper;
import com.example.crmdemo.modules.system.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author crm
 * @since 2021-10-09
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

    @Resource
    private TUserMapper tUserMapper;

    @Override
    public IPage findAll(Page page, Wrapper<TUser> queryWrapper) {
        IPage iPage = tUserMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public List<TUser> findAll(Wrapper<TUser> queryWrapper) {
        return tUserMapper.selectList(queryWrapper);
    }

    @Override
    public boolean update(TUser tUser) {
        tUser.setUpdateDate(new Date());
        int n = tUserMapper.updateById(tUser);
        if(n == 0 ){
            return false;
        }
        return true;
    }


    @Override
    public boolean add(TUser tUser) {
        tUser.setCreateDate(new Date());
        int n = tUserMapper.insert(tUser);
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
            TUser tUser = tUserMapper.selectById(m);
            int n = tUserMapper.deleteById(tUser.getId());
            if(n == 0 ){
                flg.set(false);
            }
        });
        return flg.get();
    }

    @Override
    public boolean updateById(String ids) {
        AtomicBoolean flg= new AtomicBoolean(true);
        String[] array = ids.split(",");
        Arrays.stream(array).forEach(m -> {
            TUser tUser = tUserMapper.selectById(m);
            tUser.setIsValid(1);
            int n = tUserMapper.updateById(tUser);
            if(n == 0 ){
                flg.set(false);
            }
        });
        return flg.get();
    }
}
