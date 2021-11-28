package com.example.crmdemo.modules.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.customer.model.TCusLoss;
import com.example.crmdemo.modules.customer.mapper.TCusLossMapper;
import com.example.crmdemo.modules.customer.service.TCusLossService;
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
public class TCusLossServiceImpl extends ServiceImpl<TCusLossMapper, TCusLoss> implements TCusLossService {

    @Resource
    TCusLossMapper tCusLossMapper;

    @Override
    public IPage findAll(Page page, Wrapper<TCusLoss> queryWrapper) {
        IPage iPage = tCusLossMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public List<TCusLoss> findAll(Wrapper<TCusLoss> queryWrapper) {
        return tCusLossMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> query(Wrapper<TCusLoss> queryWrapper) {
        return tCusLossMapper.selectMaps(queryWrapper);
    }

    @Override
    public boolean update(TCusLoss tCusLoss) {
        tCusLoss.setUpdateDate(new Date());
        int n = tCusLossMapper.updateById(tCusLoss);
        if(n == 0 ){
            return false;
        }
        return true;
    }

    @Override
    public boolean add(TCusLoss tCusLoss) {
        tCusLoss.setCreateDate(new Date());
        int n = tCusLossMapper.insert(tCusLoss);
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
            TCusLoss tCusLoss = tCusLossMapper.selectById(m);
            int n = tCusLossMapper.deleteById(tCusLoss.getId());
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
            TCusLoss tCusLoss = tCusLossMapper.selectById(m);
            tCusLoss.setIsValid(1);
            int n = tCusLossMapper.updateById(tCusLoss);
            if(n == 0 ){
                flg.set(false);
            }
        });
        return flg.get();
    }

    @Override
    public boolean updatePart(TCusLoss tCusLoss, Wrapper<TCusLoss> updateWrapper) {
        return false;
    }
}
