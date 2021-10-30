package com.example.crmdemo.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.system.model.TUser;
import com.example.crmdemo.modules.system.mapper.TUserMapper;
import com.example.crmdemo.modules.system.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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


}
