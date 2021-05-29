package com.ujiuye.controller;


import com.ujiuye.pojo.Setmeal;
import com.ujiuye.service.ISetmealService;
import com.ujiuye.utils.util.*;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * <p>
 * 套餐 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {


    @Reference
    private ISetmealService setmealService;


    @RequestMapping("/listPage")
    public PageResult listPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult pageResult = setmealService.listPage(queryPageBean);
            return pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResult(0L,null);
        }

    }
    @RequestMapping("/getInfo/{id}")
    public Result getInfo(@PathVariable("id") Integer id){

        try {
            Setmeal setmeal = setmealService.getInfo(id);
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }



}

