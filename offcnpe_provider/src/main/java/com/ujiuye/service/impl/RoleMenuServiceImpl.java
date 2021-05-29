package com.ujiuye.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ujiuye.mapper.RoleMenuMapper;
import com.ujiuye.pojo.RoleMenu;
import com.ujiuye.service.IRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表与菜单表的中间表，一个角色有多个菜单 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

}
