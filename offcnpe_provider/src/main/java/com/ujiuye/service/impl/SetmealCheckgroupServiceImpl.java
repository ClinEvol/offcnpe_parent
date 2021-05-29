package com.ujiuye.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ujiuye.mapper.SetmealCheckgroupMapper;
import com.ujiuye.pojo.SetmealCheckgroup;
import com.ujiuye.service.ISetmealCheckgroupService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 套餐与检查组，一个套餐有多个检查组 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class SetmealCheckgroupServiceImpl extends ServiceImpl<SetmealCheckgroupMapper, SetmealCheckgroup> implements ISetmealCheckgroupService {

}
