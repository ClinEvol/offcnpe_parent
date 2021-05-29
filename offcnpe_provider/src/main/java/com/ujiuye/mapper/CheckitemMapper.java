package com.ujiuye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ujiuye.pojo.Checkgroup;
import com.ujiuye.pojo.Checkitem;

/**
 * <p>
 * 检查项 Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface CheckitemMapper extends BaseMapper<Checkitem> {
    Checkitem getById(int id);
}
