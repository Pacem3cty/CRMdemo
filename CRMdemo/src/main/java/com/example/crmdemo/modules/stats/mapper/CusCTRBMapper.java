package com.example.crmdemo.modules.stats.mapper;




import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.crmdemo.modules.stats.dto.CusCTRBDto;
import com.example.crmdemo.modules.stats.model.CusCTRB;
import com.example.crmdemo.modules.stats.model.EchartsPiePojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

//    @Select(" SELECT t_cus.id AS id, cus_num AS cusNum,cus_name AS cusName,cus_phone AS cusPhone,cus_manager AS cusManager, SUM(order_total) AS ctrb, COUNT(t_cus_order.id) AS orderCount,SUM(order_total) / COUNT(t_cus_order.id) as aov FROM t_cus_order,t_cus ${ew.customSqlSegment}")
//    List<CusCTRB> pageListCusCTRB(Page page,@Param("ew") Wrapper wrapper);//联表查询各客户（订单）累计贡献

    List<CusCTRB> pageListCusStats(Page page, @Param("cusCTRBDto")CusCTRBDto cusCTRBDto);//联表查询各客户（订单）累计贡献

    List<EchartsPiePojo> queryOrderStats();//联表查询各客户有效订单量统计

    List<EchartsPiePojo> queryTotalStats();//联表查询各客户订单总额统计

    List<EchartsPiePojo> queryAovStats();//联表查询各客户平均订单价值统计

    List<EchartsPiePojo> queryLevelStats();//查询客户构成信息
}
