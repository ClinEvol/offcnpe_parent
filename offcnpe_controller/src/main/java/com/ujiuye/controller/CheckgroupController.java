package com.ujiuye.controller;


import com.ujiuye.pojo.Checkgroup;
import com.ujiuye.service.ICheckgroupService;
import com.ujiuye.utils.util.MessageConstant;
import com.ujiuye.utils.util.PageResult;
import com.ujiuye.utils.util.QueryPageBean;
import com.ujiuye.utils.util.Result;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 检查组 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckgroupController {
    @Reference
    private ICheckgroupService iCheckgroupService;

    @PostMapping("/save")
    public Result save(@RequestBody Checkgroup checkgroup,Integer[] checkitemids){
        try {
            iCheckgroupService.save(checkgroup,checkitemids);
            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    @GetMapping("/listAll")
    public Result listAll(){
        try {
            List<Checkgroup> list = iCheckgroupService.list();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }



    @PostMapping("/update")
    public Result update(@RequestBody Checkgroup checkgroup,Integer[] checkitemids){
        try {
            iCheckgroupService.update(checkgroup,checkitemids);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }



    @PostMapping("/listPage")
    public PageResult listPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult pageResult = iCheckgroupService.listPage(queryPageBean);
            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResult(0L,null);
        }
    }


    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Integer id){
        try {
            Checkgroup checkgroup = iCheckgroupService.getById(id);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkgroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("remove")
    public Result remove(Integer id){
        try {
            iCheckgroupService.removeById(id);
            return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }

}

