package com.example.crmdemo.modules.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.customer.model.TCusOrder;
import com.example.crmdemo.modules.service.model.TCusService;
import com.example.crmdemo.modules.service.mapper.TCusServiceMapper;
import com.example.crmdemo.modules.service.service.TCusServiceService;
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
 * @since 2021-12-03
 */
@Service
public class TCusServiceServiceImpl extends ServiceImpl<TCusServiceMapper, TCusService> implements TCusServiceService {

    @Resource
    TCusServiceMapper tCusServiceMapper;

    @Override
    public IPage findAll(Page page, Wrapper<TCusService> queryWrapper) {
        IPage iPage = tCusServiceMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public List<Map<String, Object>> query(Wrapper<TCusService> queryWrapper) {
        return tCusServiceMapper.selectMaps(queryWrapper);
    }

    @Override
    public boolean add(TCusService tSaleChance) {
        tSaleChance.setCreateDate(new Date());
        int n = tCusServiceMapper.insert(tSaleChance);
        if(n == 0 ){
            return false;
        }
        return true;
    }

    @Override
    public boolean update(TCusService tSaleChance) {
        tSaleChance.setUpdateDate(new Date());
        int n = tCusServiceMapper.updateById(tSaleChance);
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
            TCusService tSaleChance = tCusServiceMapper.selectById(m);
            int n = tCusServiceMapper.deleteById(tSaleChance.getId());
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
            TCusService tSaleChance = tCusServiceMapper.selectById(m);
            tSaleChance.setIsValid(1);
            int n = tCusServiceMapper.updateById(tSaleChance);
            if(n == 0 ){
                flg.set(false);
            }
        });
        return flg.get();
    }

    @Override
    public List<TCusService> findAll(Wrapper<TCusService> queryWrapper) {
        return tCusServiceMapper.selectList(queryWrapper);
    }

    @Override
    public boolean updatePart(TCusService tSaleChance, Wrapper<TCusService> updateWrapper) {
        AtomicBoolean flg= new AtomicBoolean(true);
        int n = tCusServiceMapper.update(tSaleChance,updateWrapper);
        if(n == 0 ){
            flg.set(false);
        }
        return flg.get();
    }

}
