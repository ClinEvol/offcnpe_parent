package com.ujiuye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ujiuye.pojo.RolePermission;

/**
 * <p>
 * 角色与权限的中间表，一个角色有多个权限	 Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

}
