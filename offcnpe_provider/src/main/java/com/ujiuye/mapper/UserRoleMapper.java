package com.ujiuye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ujiuye.pojo.UserRole;

/**
 * <p>
 * 员工表与角色表的中间表，一个员工有多个角色 Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
