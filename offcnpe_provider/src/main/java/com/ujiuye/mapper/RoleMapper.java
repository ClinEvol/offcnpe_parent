package com.ujiuye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ujiuye.pojo.Role;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> listByUserID(int user_id);
}
