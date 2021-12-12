package com.example.crmdemo.modules.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.customer.model.TCusReprieve;
import com.example.crmdemo.modules.customer.mapper.TCusReprieveMapper;
import com.example.crmdemo.modules.customer.service.TCusReprieveService;
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
public class TCusReprieveServiceImpl extends ServiceImpl<TCusReprieveMapper, TCusReprieve> implements TCusReprieveService {

    @Resource
    TCusReprieveMapper tCusReprieveMapper;

    @Override
    public IPage findAll(Page page, Wrapper<TCusReprieve> queryWrapper) {
        IPage iPage = tCusReprieveMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public List<TCusReprieve> findAll(Wrapper<TCusReprieve> queryWrapper) {
        return tCusReprieveMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> query(Wrapper<TCusReprieve> queryWrapper) {
        return tCusReprieveMapper.selectMaps(queryWrapper);
    }

    @Override
    public boolean update(TCusReprieve tCusReprieve) {
        tCusReprieve.setUpdateDate(new Date());
        int n = tCusReprieveMapper.updateById(tCusReprieve);
        if(n == 0 ){
            return false;
        }
        return true;
    }

    @Override
    public boolean add(TCusReprieve tCusReprieve) {
        tCusReprieve.setCreateDate(new Date());
        int n = tCusReprieveMapper.insert(tCusReprieve);
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
            TCusReprieve tCusReprieve = tCusReprieveMapper.selectById(m);
            int n = tCusReprieveMapper.deleteById(tCusReprieve.getId());
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
            TCusReprieve tCusReprieve = tCusReprieveMapper.selectById(m);
            tCusReprieve.setIsValid(1);
            int n = tCusReprieveMapper.updateById(tCusReprieve);
            if(n == 0 ){
                flg.set(false);
            }
        });
        return flg.get();
    }

    @Override
    public boolean updatePart(TCusReprieve tCusReprieve, Wrapper<TCusReprieve> updateWrapper) {
        AtomicBoolean flg= new AtomicBoolean(true);
        int n = tCusReprieveMapper.update(tCusReprieve,updateWrapper);
        if(n == 0 ){
            flg.set(false);
        }
        return flg.get();
    }
}
