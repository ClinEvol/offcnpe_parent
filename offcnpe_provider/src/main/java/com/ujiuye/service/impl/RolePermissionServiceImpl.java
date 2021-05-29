package com.ujiuye.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ujiuye.mapper.RolePermissionMapper;
import com.ujiuye.pojo.RolePermission;
import com.ujiuye.service.IRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与权限的中间表，一个角色有多个权限	 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

}
