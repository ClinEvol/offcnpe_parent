package com.ujiuye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.ujiuye.mapper.MenuMapper;
import com.ujiuye.pojo.Menu;
import com.ujiuye.pojo.MenuTree;
import com.ujiuye.service.IMenuService;
import org.apache.dubbo.config.annotation.Service;


import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public boolean removeById(Serializable id) {
        Menu menu = menuMapper.selectById(id);
        System.out.println(menu);
        if(menu.getLevel()==1){//删除的如果一级菜单，先删除子菜单
            QueryWrapper<Menu> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("parentMenuId",menu.getId());
            menuMapper.delete(queryWrapper);
        }
        menuMapper.deleteById(id);
        return true;
    }


    @Override
    public List<MenuTree> listMenuTree() {
        List<MenuTree> list=new ArrayList<>();
        QueryWrapper<Menu> queryWrapper=new QueryWrapper<>();
        queryWrapper.isNull("parentMenuId");
        List<Menu> menuList = menuMapper.selectList(queryWrapper);//查询所有一级菜单
        if(menuList==null || menuList.size()==0){//没有查询到一级菜单
            return list;
        }
        //有查询到一级菜单
        for (Menu menu : menuList) {
            MenuTree menuTree = new MenuTree(menu.getId(), menu.getName(),menu.getIcon());
            //查子菜单
            QueryWrapper<Menu> queryWrapper2=new QueryWrapper<>();
            queryWrapper2.eq("parentMenuId",menuTree.getId());
            List<Menu> menuList2 = menuMapper.selectList(queryWrapper2);
            if(menuList2!=null && menuList2.size()>0){
                List<MenuTree> list2=new ArrayList<>();
                for (Menu menu2 : menuList2) {
                    MenuTree menuTree2 = new MenuTree(menu2.getId(), menu2.getName(),menu.getIcon());
                    list2.add(menuTree2);
                }
                menuTree.setChildren(list2);
            }
            list.add(menuTree);

        }
        return list;
    }

    @Override
    public List<Menu> parentList() {
        QueryWrapper<Menu> queryWrapper=new QueryWrapper<>();
        queryWrapper.isNull("parentMenuId");
        List<Menu> menuList = menuMapper.selectList(queryWrapper);//查询所有一级菜单
        return menuList;
    }

}
