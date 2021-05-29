package com.ujiuye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ujiuye.pojo.Menu;
import com.ujiuye.pojo.MenuTree;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface IMenuService extends IService<Menu> {
    List<MenuTree> listMenuTree();
    List<Menu> parentList();
}
