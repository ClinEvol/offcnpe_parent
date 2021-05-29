package com.ujiuye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ujiuye.pojo.Checkitem;
import com.ujiuye.utils.util.PageResult;
import com.ujiuye.utils.util.QueryPageBean;

/**
 * <p>
 * 检查项 服务类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface ICheckitemService extends IService<Checkitem> {
    PageResult listPage(QueryPageBean queryPageBean);
}
