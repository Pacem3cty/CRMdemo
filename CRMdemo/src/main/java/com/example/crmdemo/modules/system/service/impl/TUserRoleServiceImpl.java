package com.example.crmdemo.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.system.model.TUser;
import com.example.crmdemo.modules.system.model.TUserRole;
import com.example.crmdemo.modules.system.mapper.TUserRoleMapper;
import com.example.crmdemo.modules.system.service.TUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
public class TUserRoleServiceImpl extends ServiceImpl<TUserRoleMapper, TUserRole> implements TUserRoleService {

    @Resource
    TUserRoleMapper tUserRoleMapper;

    @Override
    public IPage findAll(Page page, Wrapper<TUserRole> queryWrapper) {
        IPage iPage = tUserRoleMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public List<TUserRole> findAll(Wrapper<TUserRole> queryWrapper) {
        return tUserRoleMapper.selectList(queryWrapper);
    }

    @Override
    public boolean update(TUserRole tUserRole) {
        tUserRole.setUpdateDate(new Date());
        int n = tUserRoleMapper.updateById(tUserRole);
        if(n == 0 ){
            return false;
        }
        return true;
    }

    @Override
    public boolean add(TUserRole tUserRole) {
        tUserRole.setCreateDate(new Date());
        int n = tUserRoleMapper.insert(tUserRole);
        if(n == 0 ){
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String ids) {//联表使用硬删除
        AtomicBoolean flg= new AtomicBoolean(true);
        String[] array = ids.split(",");
        Arrays.stream(array).forEach(m -> {
            TUserRole tUserRole = tUserRoleMapper.selectById(m);
            int n = tUserRoleMapper.deleteById(tUserRole.getId());
            if(n == 0 ){
                flg.set(false);
            }
        });
        return flg.get();
    }

    @Override
    public boolean updateById(String ids) {//聯表暫時不考虑不提供軟刪除
        return false;
    }
}
