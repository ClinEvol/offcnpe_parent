package com.ujiuye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ujiuye.pojo.Permission;
import com.ujiuye.utils.util.PageResult;
import com.ujiuye.utils.util.QueryPageBean;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface IPermissionService extends IService<Permission> {

    PageResult listPage(QueryPageBean queryPageBean);
    List<Permission> listByUserId(int user_id);
}
