package com.ujiuye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ujiuye.pojo.RoleMenu;

/**
 * <p>
 * 角色表与菜单表的中间表，一个角色有多个菜单 Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

}
