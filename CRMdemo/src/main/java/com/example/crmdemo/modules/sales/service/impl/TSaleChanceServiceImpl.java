package com.example.crmdemo.modules.sales.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.sales.model.TSaleChance;
import com.example.crmdemo.modules.sales.mapper.TSaleChanceMapper;
import com.example.crmdemo.modules.sales.service.TSaleChanceService;
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
 * @since 2021-10-20
 */
@Service
public class TSaleChanceServiceImpl extends ServiceImpl<TSaleChanceMapper, TSaleChance> implements TSaleChanceService {

    @Resource
    TSaleChanceMapper tSaleChanceMapper;

    @Override
    public IPage findAll(Page page, Wrapper<TSaleChance> queryWrapper) {
        IPage iPage = tSaleChanceMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    @Override
    public List<Map<String, Object>> query(Wrapper<TSaleChance> queryWrapper) {
        return tSaleChanceMapper.selectMaps(queryWrapper);
    }

    @Override
    public boolean add(TSaleChance tSaleChance) {
        tSaleChance.setCreateDate(new Date());
        int n = tSaleChanceMapper.insert(tSaleChance);
        if (n == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean update(TSaleChance tSaleChance) {
        tSaleChance.setUpdateDate(new Date());
        int n = tSaleChanceMapper.updateById(tSaleChance);
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
            TSaleChance tSaleChance = tSaleChanceMapper.selectById(m);
            int n = tSaleChanceMapper.deleteById(tSaleChance.getId());
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
            TSaleChance tSaleChance = tSaleChanceMapper.selectById(m);
            tSaleChance.setIsValid(1);
            int n = tSaleChanceMapper.updateById(tSaleChance);
            if (n == 0) {
                flg.set(false);
            }
        });
        return flg.get();
    }

    @Override
    public List<TSaleChance> findAll(Wrapper<TSaleChance> queryWrapper) {
        return tSaleChanceMapper.selectList(queryWrapper);
    }
}
