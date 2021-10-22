package com.example.crmdemo.modules.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.crmdemo.common.api.CommonResult;
import com.example.crmdemo.modules.basic.model.TUser;
import com.example.crmdemo.modules.basic.mapper.TUserMapper;
import com.example.crmdemo.modules.basic.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
