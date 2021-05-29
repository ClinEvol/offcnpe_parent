package com.ujiuye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ujiuye.mapper.UserMapper;
import com.ujiuye.mapper.UserRoleMapper;
import com.ujiuye.pojo.Checkitem;
import com.ujiuye.pojo.RoleMenu;
import com.ujiuye.pojo.User;
import com.ujiuye.pojo.UserRole;
import com.ujiuye.service.IUserService;
import com.ujiuye.utils.util.PageResult;
import com.ujiuye.utils.util.QueryPageBean;
import org.apache.dubbo.config.annotation.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 员工表 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Override
    public User getByUserName(String username) {
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("username",username);
        return userMapper.selectOne(userQueryWrapper);
    }

    @Override
    public PageResult listQuery(QueryPageBean queryPageBean) {
        //创建分页对象
        Page<User> page=new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //设置查询条件
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        //注间这里写表的列名称，不是写实全类的属性名称
        if(queryPageBean.getQueryString()!=null && !queryPageBean.getQueryString().equals("")){//有查条件
            queryWrapper.like("username",queryPageBean.getQueryString())
                    .or().like("telephone",queryPageBean.getQueryString());
        }
        //没有查询条件不是查全部
        Page<User> userPage = userMapper.selectPage(page, queryWrapper);
        return new PageResult(userPage.getTotal(), userPage.getRecords());
    }

    @Override
    public boolean saveUser(User user) {

        userMapper.insert(user);
        List<Integer> roleIds = user.getRoleIds();
        for (Integer roleId : roleIds) {
            UserRole userRole=new UserRole(user.getId(),roleId);
            userRoleMapper.insert(userRole);
        }
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        userMapper.updateById(user);
        //删除原本的角色
        QueryWrapper<UserRole> userRoleQueryWrapper=new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id",user.getId());
        userRoleMapper.delete(userRoleQueryWrapper);
        //添加新的角色
        List<Integer> roleIds = user.getRoleIds();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(roleIds);
        for (Integer roleId : roleIds) {
            UserRole userRole=new UserRole(user.getId(),roleId);
            userRoleMapper.insert(userRole);
        }
        return true;
    }

    @Override
    public User getUser(int id) {
        QueryWrapper<UserRole> userRoleQueryWrapper=new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id",id);
        List<UserRole> userRoles = userRoleMapper.selectList(userRoleQueryWrapper);
        List<Integer> roleIds=new ArrayList<>();
        for (UserRole userRole : userRoles) {
            roleIds.add(userRole.getRoleId());
        }
        User user = userMapper.selectById(id);
        user.setRoleIds(roleIds);
        return user;
    }


}
