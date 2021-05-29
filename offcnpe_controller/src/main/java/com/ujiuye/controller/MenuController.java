package com.ujiuye.controller;


import com.ujiuye.pojo.Menu;
import com.ujiuye.pojo.MenuTree;
import com.ujiuye.pojo.Permission;
import com.ujiuye.service.IMenuService;
import com.ujiuye.utils.util.MessageConstant;
import com.ujiuye.utils.util.Result;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Reference
    private IMenuService menuService;

    @GetMapping("/listMenuTree")
    public Result listMenuTree(){
        try{
            List<MenuTree> menuTrees = menuService.listMenuTree();
            if(menuTrees!=null){
                return new Result(true, MessageConstant.QUERY_MENU_SUCCESS,menuTrees);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false, MessageConstant.QUERY_MENU_FAIL);
    }

    @GetMapping("/parentlist")
    public Result parentlist(){
        try{
            List<Menu> menus = menuService.parentList();
            return new Result(true, MessageConstant.QUERY_MENU_SUCCESS,menus);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false, MessageConstant.QUERY_MENU_FAIL);

    }

    @PostMapping("/save")
    public Result save(@RequestBody Menu menu){
        try {
            if(menu.getParentmenuid()!=null){
                menu.setLevel(2);
            }else{
                menu.setLevel(1);
            }
            menuService.save(menu);
            return new Result(true, MessageConstant.ADD_MENU_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_MENU_FAIL);
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Menu menu){
        try {
            menuService.updateById(menu);
            return new Result(true, MessageConstant.EDIT_MENU_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_MENU_FAIL);
        }
    }

    @GetMapping("/remove/{id}")
    public Result remove(@PathVariable("id") int id){
        try {
            menuService.removeById(id);
            return new Result(true, MessageConstant.DELETE_MENU_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_MENU_FAIL);
        }
    }

    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") int id){
        try {
            Menu menu = menuService.getById(id);
            return new Result(true, MessageConstant.DELETE_MENU_SUCCESS,menu);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_MENU_FAIL);
        }
    }
}

