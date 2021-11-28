package com.example.crmdemo.modules.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.crmdemo.modules.customer.model.TCusLinkman;
import com.example.crmdemo.modules.customer.mapper.TCusLinkmanMapper;
import com.example.crmdemo.modules.customer.service.TCusLinkmanService;
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
public class TCusLinkmanServiceImpl extends ServiceImpl<TCusLinkmanMapper, TCusLinkman> implements TCusLinkmanService {

    @Resource
    TCusLinkmanMapper tCusLinkmanMapper;

    @Override
    public IPage findAll(Page page, Wrapper<TCusLinkman> queryWrapper) {
        IPage iPage = tCusLinkmanMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public List<TCusLinkman> findAll(Wrapper<TCusLinkman> queryWrapper) {
        return tCusLinkmanMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> query(Wrapper<TCusLinkman> queryWrapper) {
        return tCusLinkmanMapper.selectMaps(queryWrapper);
    }

    @Override
    public boolean update(TCusLinkman tCusLinkman) {
        tCusLinkman.setUpdateDate(new Date());
        int n = tCusLinkmanMapper.updateById(tCusLinkman);
        if(n == 0 ){
            return false;
        }
        return true;
    }

    @Override
    public boolean add(TCusLinkman tCusLinkman) {
        tCusLinkman.setCreateDate(new Date());
        int n = tCusLinkmanMapper.insert(tCusLinkman);
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
            TCusLinkman tCusLinkman = tCusLinkmanMapper.selectById(m);
            int n = tCusLinkmanMapper.deleteById(tCusLinkman.getId());
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
            TCusLinkman tCusLinkman = tCusLinkmanMapper.selectById(m);
            tCusLinkman.setIsValid(1);
            int n = tCusLinkmanMapper.updateById(tCusLinkman);
            if(n == 0 ){
                flg.set(false);
            }
        });
        return flg.get();
    }

    @Override
    public boolean updatePart(TCusLinkman tCusLinkman, Wrapper<TCusLinkman> updateWrapper) {
        return false;
    }
}
