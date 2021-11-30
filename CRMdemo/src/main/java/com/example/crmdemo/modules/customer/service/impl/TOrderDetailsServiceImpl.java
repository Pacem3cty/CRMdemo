package com.example.crmdemo.modules.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.customer.model.TOrderDetails;
import com.example.crmdemo.modules.customer.mapper.TOrderDetailsMapper;
import com.example.crmdemo.modules.customer.service.TOrderDetailsService;
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
 * @since 2021-11-24
 */
@Service
public class TOrderDetailsServiceImpl extends ServiceImpl<TOrderDetailsMapper, TOrderDetails> implements TOrderDetailsService {

    @Resource
    TOrderDetailsMapper tOrderDetailsMapper;

    @Override
    public IPage findAll(Page page, Wrapper<TOrderDetails> queryWrapper) {
        IPage iPage = tOrderDetailsMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public List<TOrderDetails> findAll(Wrapper<TOrderDetails> queryWrapper) {
        return tOrderDetailsMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> query(Wrapper<TOrderDetails> queryWrapper) {
        return tOrderDetailsMapper.selectMaps(queryWrapper);
    }

    @Override
    public boolean update(TOrderDetails tOrderDetails) {
        tOrderDetails.setUpdateDate(new Date());
        int n = tOrderDetailsMapper.updateById(tOrderDetails);
        if(n == 0 ){
            return false;
        }
        return true;
    }

    @Override
    public boolean add(TOrderDetails tOrderDetails) {
        tOrderDetails.setCreateDate(new Date());
        int n = tOrderDetailsMapper.insert(tOrderDetails);
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
            TOrderDetails tOrderDetails = tOrderDetailsMapper.selectById(m);
            int n = tOrderDetailsMapper.deleteById(tOrderDetails.getId());
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
            TOrderDetails tOrderDetails = tOrderDetailsMapper.selectById(m);
            tOrderDetails.setIsValid(1);
            int n = tOrderDetailsMapper.updateById(tOrderDetails);
            if(n == 0 ){
                flg.set(false);
            }
        });
        return flg.get();
    }

    @Override
    public boolean updatePart(TOrderDetails tOrderDetails, Wrapper<TOrderDetails> updateWrapper) {
        AtomicBoolean flg= new AtomicBoolean(true);
        int n = tOrderDetailsMapper.update(tOrderDetails,updateWrapper);
        if(n == 0 ){
            flg.set(false);
        }
        return flg.get();
    }
}
