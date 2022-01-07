package com.example.crmdemo.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.system.model.TDict;
import com.example.crmdemo.modules.system.mapper.TDictMapper;
import com.example.crmdemo.modules.system.service.TDictService;
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
 * @since 2022-01-07
 */
@Service
public class TDictServiceImpl extends ServiceImpl<TDictMapper, TDict> implements TDictService {

    @Resource
    private TDictMapper tDictMapper;

    @Override
    public IPage findAll(Page page, Wrapper<TDict> queryWrapper) {
        IPage iPage = tDictMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    @Override
    public List<TDict> findAll(Wrapper<TDict> queryWrapper) {
        return tDictMapper.selectList(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> query(Wrapper<TDict> queryWrapper) {
        return tDictMapper.selectMaps(queryWrapper);
    }

    @Override
    public boolean update(TDict tDict) {
        tDict.setUpdateDate(new Date());
        int n = tDictMapper.updateById(tDict);
        if (n == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean add(TDict tDict) {
        tDict.setCreateDate(new Date());
        int n = tDictMapper.insert(tDict);
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
            TDict tDict = tDictMapper.selectById(m);
            int n = tDictMapper.deleteById(tDict.getId());
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
            TDict tDict = tDictMapper.selectById(m);
            tDict.setIsValid(1);
            int n = tDictMapper.updateById(tDict);
            if (n == 0) {
                flg.set(false);
            }
        });
        return flg.get();
    }
}
