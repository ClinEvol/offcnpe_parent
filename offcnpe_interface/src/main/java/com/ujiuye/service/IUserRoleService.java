package com.ujiuye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ujiuye.pojo.UserRole;

/**
 * <p>
 * 员工表与角色表的中间表，一个员工有多个角色 服务类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface IUserRoleService extends IService<UserRole> {

}
