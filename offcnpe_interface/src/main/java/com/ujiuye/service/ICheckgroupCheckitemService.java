package com.ujiuye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ujiuye.pojo.CheckgroupCheckitem;
import com.ujiuye.utils.util.Result;

/**
 * <p>
 * 栓查项与检查组的中间表，一个检查组有多个检查项 服务类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface ICheckgroupCheckitemService extends IService<CheckgroupCheckitem> {

    Result listByCheckgroupId(Integer checkgroupId);
}

