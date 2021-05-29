package com.ujiuye.controller;


import com.ujiuye.pojo.Checkgroup;
import com.ujiuye.pojo.Permission;
import com.ujiuye.service.IPermissionService;
import com.ujiuye.utils.util.MessageConstant;
import com.ujiuye.utils.util.PageResult;
import com.ujiuye.utils.util.QueryPageBean;
import com.ujiuye.utils.util.Result;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Reference
    private IPermissionService permissionService;

    @PostMapping("/listPage")
    public PageResult listPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult pageResult = permissionService.listPage(queryPageBean);
            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResult(0L,null);
        }
    }

    @GetMapping("/listAll")
    public Result listAll(){
        try {
            List<Permission> list = permissionService.list();
            return new Result(true,MessageConstant.QUERY_PERMISSION_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,MessageConstant.QUERY_PERMISSION_FAIL);
        }
    }


    @PostMapping("/save")
    public Result save(@RequestBody Permission permission){
        try {
            permissionService.save(permission);
            return new Result(true, MessageConstant.ADD_PERMISSION_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_PERMISSION_FAIL);
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Permission permission){
        try {
            permissionService.updateById(permission);
            return new Result(true, MessageConstant.EDIT_PERMISSION_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_PERMISSION_FAIL);
        }
    }

    @GetMapping("/remove/{id}")
    public Result remove(@PathVariable("id") int id){
        try {
            permissionService.removeById(id);
            return new Result(true, MessageConstant.DELETE_PERMISSION_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_PERMISSION_FAIL);
        }
    }

    @GetMapping("/get/{id}")
    public Result get(@PathVariable("id") int id){
        try {
            Permission permission = permissionService.getById(id);
            return new Result(true, MessageConstant.DELETE_PERMISSION_SUCCESS,permission);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_PERMISSION_FAIL);
        }
    }

}

