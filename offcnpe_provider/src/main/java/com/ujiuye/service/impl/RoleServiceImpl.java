package com.ujiuye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ujiuye.mapper.RoleMapper;
import com.ujiuye.mapper.RoleMenuMapper;
import com.ujiuye.mapper.RolePermissionMapper;
import com.ujiuye.pojo.*;
import com.ujiuye.service.IRoleService;
import com.ujiuye.utils.util.PageResult;
import com.ujiuye.utils.util.QueryPageBean;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Override
    public List<Role> listQuery(QueryPageBean queryPageBean) {
        String queryString=queryPageBean.getQueryString();

        QueryWrapper<Role> queryWrapper=new QueryWrapper<>();
        if(queryString!=null && !queryString.equals("")){
            queryWrapper.like("name",queryString)
                    .or().like("keyword",queryString);
        }
        return roleMapper.selectList(queryWrapper);
    }

    @Override
    public List<Role> listByUserID(int user_id) {
        return roleMapper.listByUserID(user_id);
    }

    @Override
    public boolean save(Role role) {
        roleMapper.insert(role);
        //添加菜单
        List<Integer> menuIds = role.getMenuIds();
        for (Integer menuId : menuIds) {
            RoleMenu roleMenu=new RoleMenu(role.getId(),menuId);
            roleMenuMapper.insert(roleMenu);
        }
        //添加权限
        List<Integer> permissionIds = role.getPermissionIds();
        for (Integer permissionId : permissionIds) {
            RolePermission permission=new RolePermission(role.getId(),permissionId);
            rolePermissionMapper.insert(permission);
        }
        return true;
    }

    @Override
    public boolean updateById(Role role) {


        //删除原本菜单
        QueryWrapper<RoleMenu> roleMenuQueryWrapper=new QueryWrapper<>();
        roleMenuQueryWrapper.eq("role_id",role.getId());
        int delete = roleMenuMapper.delete(roleMenuQueryWrapper);
        //添加菜单
        List<Integer> menuIds = role.getMenuIds();
        for (Integer menuId : menuIds) {
            RoleMenu roleMenu=new RoleMenu(role.getId(),menuId);
            roleMenuMapper.insert(roleMenu);
        }

        //删除原本权限
        QueryWrapper<RolePermission> rolePermissionQueryWrapper=new QueryWrapper<>();
        roleMenuQueryWrapper.eq("role_id",role.getId());
        int delete1 = rolePermissionMapper.delete(rolePermissionQueryWrapper);
        //添加权限
        List<Integer> permissionIds = role.getPermissionIds();
        for (Integer permissionId : permissionIds) {
            RolePermission permission=new RolePermission(role.getId(),permissionId);
            rolePermissionMapper.insert(permission);
        }
        roleMapper.updateById(role);
        return true;
    }

    @Override
    public Role getById(int id) {

        Role role = roleMapper.selectById(id);
        //查询原本菜单
        QueryWrapper<RoleMenu> roleMenuQueryWrapper=new QueryWrapper<>();
        roleMenuQueryWrapper.eq("role_id",id);
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(roleMenuQueryWrapper);
        List<Integer> roleMenuIds=new ArrayList<>();
        for (RoleMenu roleMenu : roleMenus) {
            roleMenuIds.add(roleMenu.getMenuId());
        }
        role.setMenuIds(roleMenuIds);
        //查询原本权限
        QueryWrapper<RolePermission> rolePermissionQueryWrapper=new QueryWrapper<>();
        roleMenuQueryWrapper.eq("role_id",id);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(rolePermissionQueryWrapper);
        List<Integer> rolePermissionsIds=new ArrayList<>();
        for (RolePermission rolePermission : rolePermissions) {
            rolePermissionsIds.add(rolePermission.getPermissionId());
        }
        role.setPermissionIds(rolePermissionsIds);
        return role;
    }

}
