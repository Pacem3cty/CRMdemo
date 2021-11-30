package com.example.crmdemo.modules.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.customer.model.TCusOrder;
import com.example.crmdemo.modules.customer.mapper.TCusOrderMapper;
import com.example.crmdemo.modules.customer.service.TCusOrderService;
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
 * @since 2021-11-29
 */
@Service
public class TCusOrderServiceImpl extends ServiceImpl<TCusOrderMapper, TCusOrder> implements TCusOrderService {

    @Resource
    TCusOrderMapper tCusOrderMapper;


    @Override
    public IPage findAll(Page page, Wrapper<TCusOrder> queryWrapper) {
        IPage iPage = tCusOrderMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public List<TCusOrder> findAll(Wrapper<TCusOrder> queryWrapper) {
        return tCusOrderMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> query(Wrapper<TCusOrder> queryWrapper) {
        return tCusOrderMapper.selectMaps(queryWrapper);
    }

    @Override
    public boolean update(TCusOrder tCusOrder) {
        tCusOrder.setUpdateDate(new Date());
        int n = tCusOrderMapper.updateById(tCusOrder);
        if(n == 0 ){
            return false;
        }
        return true;
    }

    @Override
    public boolean add(TCusOrder tCusOrder) {
        tCusOrder.setCreateDate(new Date());
        int n = tCusOrderMapper.insert(tCusOrder);
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
            TCusOrder tCusOrder = tCusOrderMapper.selectById(m);
            int n = tCusOrderMapper.deleteById(tCusOrder.getId());
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
            TCusOrder tCusOrder = tCusOrderMapper.selectById(m);
            tCusOrder.setIsValid(1);
            int n = tCusOrderMapper.updateById(tCusOrder);
            if(n == 0 ){
                flg.set(false);
            }
        });
        return flg.get();
    }

    @Override
    public boolean updatePart(TCusOrder tCusOrder, Wrapper<TCusOrder> updateWrapper) {
        AtomicBoolean flg= new AtomicBoolean(true);
        int n = tCusOrderMapper.update(tCusOrder,updateWrapper);
        if(n == 0 ){
            flg.set(false);
        }
        return flg.get();
    }
}
