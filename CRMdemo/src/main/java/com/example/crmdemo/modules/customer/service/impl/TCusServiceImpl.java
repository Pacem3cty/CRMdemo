package com.example.crmdemo.modules.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.customer.mapper.TCusOrderMapper;
import com.example.crmdemo.modules.customer.model.TCus;
import com.example.crmdemo.modules.customer.mapper.TCusMapper;
import com.example.crmdemo.modules.customer.model.TCusOrder;
import com.example.crmdemo.modules.customer.service.TCusService;
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
public class TCusServiceImpl extends ServiceImpl<TCusMapper, TCus> implements TCusService {

    @Resource
    TCusMapper tCusMapper;

    @Override
    public IPage findAll(Page page, Wrapper<TCus> queryWrapper) {
        IPage iPage = tCusMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public List<TCus> findAll(Wrapper<TCus> queryWrapper) {
        return tCusMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> query(Wrapper<TCus> queryWrapper) {
        return tCusMapper.selectMaps(queryWrapper);
    }

    @Override
    public boolean update(TCus tCus) {
        tCus.setUpdateDate(new Date());
        int n = tCusMapper.updateById(tCus);
        if(n == 0 ){
            return false;
        }
        return true;
    }

    @Override
    public boolean add(TCus tCus) {
        tCus.setCreateDate(new Date());
        int n = tCusMapper.insert(tCus);
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
            TCus tCus = tCusMapper.selectById(m);
            int n = tCusMapper.deleteById(tCus.getId());
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
            TCus tCus = tCusMapper.selectById(m);
            tCus.setIsValid(1);
            int n = tCusMapper.updateById(tCus);
            if(n == 0 ){
                flg.set(false);
            }
        });
        return flg.get();
    }

    @Override
    public boolean updatePart(TCus tCus, Wrapper<TCus> updateWrapper) {
        return false;
    }

    @Override
    public List<TCus> queryLossCus() {
        return tCusMapper.queryLossCus();
    }

    @Override
    public Integer updateCusLossStatusByIds(List<Integer> cusIds) {
        return tCusMapper.updateCusLossStatusByIds(cusIds);
    }


}
