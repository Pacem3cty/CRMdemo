package com.example.crmdemo.modules.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.customer.model.TCusContact;
import com.example.crmdemo.modules.customer.mapper.TCusContactMapper;
import com.example.crmdemo.modules.customer.service.TCusContactService;
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
public class TCusContactServiceImpl extends ServiceImpl<TCusContactMapper, TCusContact> implements TCusContactService {

    @Resource
    TCusContactMapper tCusContactMapper;

    @Override
    public IPage findAll(Page page, Wrapper<TCusContact> queryWrapper) {
        IPage iPage = tCusContactMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public List<TCusContact> findAll(Wrapper<TCusContact> queryWrapper) {
        return tCusContactMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> query(Wrapper<TCusContact> queryWrapper) {
        return tCusContactMapper.selectMaps(queryWrapper);
    }

    @Override
    public boolean update(TCusContact tCusContact) {
        tCusContact.setUpdateDate(new Date());
        int n = tCusContactMapper.updateById(tCusContact);
        if(n == 0 ){
            return false;
        }
        return true;
    }

    @Override
    public boolean add(TCusContact tCusContact) {
        tCusContact.setCreateDate(new Date());
        int n = tCusContactMapper.insert(tCusContact);
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
            TCusContact tCusContact = tCusContactMapper.selectById(m);
            int n = tCusContactMapper.deleteById(tCusContact.getId());
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
            TCusContact tCusContact = tCusContactMapper.selectById(m);
            tCusContact.setIsValid(1);
            int n = tCusContactMapper.updateById(tCusContact);
            if(n == 0 ){
                flg.set(false);
            }
        });
        return flg.get();
    }

    @Override
    public boolean updatePart(TCusContact tCusContact, Wrapper<TCusContact> updateWrapper) {
        return false;
    }
}
