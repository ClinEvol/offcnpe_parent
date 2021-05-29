package com.ujiuye.controller;


import com.ujiuye.pojo.Checkitem;
import com.ujiuye.service.ICheckitemService;
import com.ujiuye.utils.util.MessageConstant;
import com.ujiuye.utils.util.PageResult;
import com.ujiuye.utils.util.QueryPageBean;
import com.ujiuye.utils.util.Result;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 检查项 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/checkitem")
public class CheckitemController {

    @Reference
    private ICheckitemService iCheckitemService;

    @RequestMapping("save")
    @PreAuthorize("hasAuthority('ADD_DONG')")
    public Result save(@RequestBody Checkitem checkitem){
        try {
            boolean save = iCheckitemService.save(checkitem);
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("listPage")
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")
    public PageResult listPage(@RequestBody QueryPageBean queryPageBean){
        try {
            return iCheckitemService.listPage(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResult(0L,null);
        }
    }


    @RequestMapping("remove")
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")
    public Result remove(Integer id){
        try {
            iCheckitemService.removeById(id);
            return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }

    //查询被修改的数据
    @RequestMapping("getById")
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")
    public Result getById(Integer id){
        try {
            Checkitem checkitem = iCheckitemService.getById(id);
            if(checkitem==null){
                return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
            }
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkitem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    //执行修改操作
    @RequestMapping("update")
    @PreAuthorize("hasAuthority('CHECKITEM_EDIT')")
    public Result update(@RequestBody Checkitem checkitem){
        try {
            boolean save = iCheckitemService.updateById(checkitem);
            return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("listAll")
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")
    public Result listAll(){
        try {
            List<Checkitem> list = iCheckitemService.list();
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

}

