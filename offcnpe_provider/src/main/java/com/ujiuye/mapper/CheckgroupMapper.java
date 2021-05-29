package com.ujiuye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ujiuye.pojo.Checkgroup;

/**
 * <p>
 * 检查组 Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface CheckgroupMapper extends BaseMapper<Checkgroup> {
    Checkgroup getById(int id);
}


