package com.ujiuye.service;

import com.baomidou.mybatisplus.core.metadata.PageList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ujiuye.pojo.Setmeal;
import com.ujiuye.utils.util.PageResult;
import com.ujiuye.utils.util.QueryPageBean;

import java.util.Map;

/**
 * <p>
 * 套餐 服务类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface ISetmealService extends IService<Setmeal> {

    void save(Setmeal setmeal, Integer[] checkgroupIds);

    PageResult listPage(QueryPageBean queryPageBean);

    Setmeal getInfo(Integer id);

    Map<String,Object> getCountSetmeal();
}

