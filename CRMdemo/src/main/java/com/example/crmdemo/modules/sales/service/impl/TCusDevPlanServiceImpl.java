package com.example.crmdemo.modules.sales.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.sales.model.TCusDevPlan;
import com.example.crmdemo.modules.sales.mapper.TCusDevPlanMapper;
import com.example.crmdemo.modules.sales.model.TSaleChance;
import com.example.crmdemo.modules.sales.service.TCusDevPlanService;
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
 * @since 2021-10-20
 */
@Service
public class TCusDevPlanServiceImpl extends ServiceImpl<TCusDevPlanMapper, TCusDevPlan> implements TCusDevPlanService {

    @Resource
    TCusDevPlanMapper tCusDevPlanMapper;

    @Override
    public IPage findAll(Page page, Wrapper<TCusDevPlan> queryWrapper) {
        IPage iPage = tCusDevPlanMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public List<Map<String, Object>> query(Wrapper<TCusDevPlan> queryWrapper) {
        return tCusDevPlanMapper.selectMaps(queryWrapper);
    }

    @Override
    public boolean add(TCusDevPlan tCusDevPlan) {
        tCusDevPlan.setCreateDate(new Date());
        int n = tCusDevPlanMapper.insert(tCusDevPlan);
        if(n == 0 ){
            return false;
        }
        return true;
    }

    @Override
    public boolean update(TCusDevPlan tCusDevPlan) {
        tCusDevPlan.setUpdateDate(new Date());
        int n = tCusDevPlanMapper.updateById(tCusDevPlan);
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
            TCusDevPlan tCusDevPlan = tCusDevPlanMapper.selectById(m);
            int n = tCusDevPlanMapper.deleteById(tCusDevPlan.getId());
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
            TCusDevPlan tCusDevPlan = tCusDevPlanMapper.selectById(m);
            tCusDevPlan.setIsValid(1);
            int n = tCusDevPlanMapper.updateById(tCusDevPlan);
            if(n == 0 ){
                flg.set(false);
            }
        });
        return flg.get();
    }

    @Override
    public List<TCusDevPlan> findAll(Wrapper<TCusDevPlan> queryWrapper) {
        return tCusDevPlanMapper.selectList(queryWrapper);
    }
}
