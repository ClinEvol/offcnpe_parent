package com.ujiuye.service;

import com.ujiuye.pojo.Permission;
import com.ujiuye.pojo.Role;
import com.ujiuye.pojo.User;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Reference
    private IRoleService roleService;
    @Reference
    private IPermissionService permissionService;
    @Reference
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //#1、通过用户进行登录
        User user = userService.getByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException("用户名不正确！");
        }
        //创建一个角色集合用于保存这个用户的角色和权限
        List<GrantedAuthority> authorityList=new ArrayList<>();

        //2、查询这个用户有什么角色
        List<Role> roleList = roleService.listByUserID(user.getId());
        for (Role role : roleList) {
            String keyword = role.getKeyword();
            authorityList.add(new SimpleGrantedAuthority(keyword));
        }
        //3、查询这个用户有什么权限
        List<Permission> permissionList = permissionService.listByUserId(user.getId());
        for (Permission permission : permissionList) {
            String keyword = permission.getKeyword();
            authorityList.add(new SimpleGrantedAuthority(keyword));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorityList);
    }
}
