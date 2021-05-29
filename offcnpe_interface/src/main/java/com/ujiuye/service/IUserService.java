package com.ujiuye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ujiuye.pojo.User;
import com.ujiuye.utils.util.PageResult;
import com.ujiuye.utils.util.QueryPageBean;

/**
 * <p>
 * 员工表 服务类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface IUserService extends IService<User> {
    User getByUserName(String username);

    PageResult listQuery(QueryPageBean queryPageBean);

    boolean saveUser(User user);
    boolean updateUser(User user);
    User getUser(int id);
}
