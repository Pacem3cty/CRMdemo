package com.example.crmdemo.modules.stats.mapper;




import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.stats.model.CusCTRB;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wr
 * @since 2021-10-09
 */

@Mapper
public interface CusCTRBMapper extends BaseMapper<CusCTRB> {

    List<CusCTRB> pageListCusCTRB(Page page, Wrapper wrapper);//联表查询各客户（订单）累计贡献
}
