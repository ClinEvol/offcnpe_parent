package com.ujiuye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ujiuye.pojo.Role;
import com.ujiuye.utils.util.QueryPageBean;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface IRoleService extends IService<Role> {
    List<Role> listQuery(QueryPageBean queryPageBean);
    List<Role> listByUserID(int user_id);
    boolean save(Role role);
    boolean updateById(Role role);
    Role getById(int id);
}
