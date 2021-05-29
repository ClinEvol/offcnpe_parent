package com.ujiuye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ujiuye.pojo.Order;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface OrderMapper extends BaseMapper<Order> {
    List<Map<String,Object>> hotSetmeal();
}
