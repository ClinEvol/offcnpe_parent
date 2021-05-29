package com.ujiuye.controller;


import com.ujiuye.pojo.Permission;
import com.ujiuye.pojo.Role;
import com.ujiuye.service.IRoleService;
import com.ujiuye.utils.util.MessageConstant;
import com.ujiuye.utils.util.PageResult;
import com.ujiuye.utils.util.QueryPageBean;
import com.ujiuye.utils.util.Result;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Reference
    private IRoleService roleService;

    @PostMapping("/list")
    public Result list(@RequestBody QueryPageBean queryPageBean){
        try {
            List<Role> list = roleService.listQuery(queryPageBean);
            return new Result(true,MessageConstant.QUERY_ROLE_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ROLE_FAIL);
        }
    }

    @PostMapping("/listAll")
    public Result listAll(){
        try {
            List<Role> list = roleService.list();
            return new Result(true,MessageConstant.QUERY_ROLE_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ROLE_FAIL);
        }
    }


    @PostMapping("/save")
    public Result save(@RequestBody Role role){
        try {
            roleService.save(role);
            return new Result(true, MessageConstant.ADD_ROLE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_ROLE_FAIL);
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Role role){
        try {
            roleService.updateById(role);
            return new Result(true, MessageConstant.EDIT_ROLE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_ROLE_FAIL);
        }
    }

    @GetMapping("/remove/{id}")
    public Result remove(@PathVariable("id") int id){
        try {
            roleService.removeById(id);
            return new Result(true, MessageConstant.DELETE_ROLE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_ROLE_FAIL);
        }
    }

    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") int id){
        try {
            Role role = roleService.getById(id);
            return new Result(true, MessageConstant.DELETE_ROLE_SUCCESS,role);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_ROLE_FAIL);
        }
    }
}

