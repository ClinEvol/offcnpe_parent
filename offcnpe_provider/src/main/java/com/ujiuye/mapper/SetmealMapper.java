package com.ujiuye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ujiuye.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 套餐 Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface SetmealMapper extends BaseMapper<Setmeal> {
    List<Map<String,Object>> getCountSetmeal();
}
