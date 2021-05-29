package com.ujiuye.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ujiuye.mapper.UserRoleMapper;
import com.ujiuye.pojo.UserRole;
import com.ujiuye.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工表与角色表的中间表，一个员工有多个角色 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
