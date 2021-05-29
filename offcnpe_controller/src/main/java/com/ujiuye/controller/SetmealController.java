package com.ujiuye.controller;


import com.ujiuye.pojo.Setmeal;
import com.ujiuye.service.ISetmealService;
import com.ujiuye.utils.util.*;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Map;

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

    @Value("${fileUploadPath}")
    private String fileUploadPath;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    //上传图片   返回片的名字
    @RequestMapping("/upload")
    public String upload(@RequestParam("imgFile") MultipartFile multipartFile){
        File file = MyFileUtils.upload(multipartFile,fileUploadPath);
        if(file!=null){//文件上传成功
            redisTemplate.opsForSet().add(RedisConstant.SETMEAL_PIC_UPLOAD,file.getName());
        }
        return file.getName();
    }

    @Reference
    private ISetmealService iSetmealService;

    @RequestMapping("/save")
    public Result save(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        try {
            iSetmealService.save(setmeal,checkgroupIds);
            //保存数据成功了
            redisTemplate.opsForSet().add(RedisConstant.SETMEAL_PIC_DB,setmeal.getImg().substring(9));
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
    }


    @RequestMapping("listPage")
    public PageResult listPage(@RequestBody QueryPageBean queryPageBean){
        try {
            return iSetmealService.listPage(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResult(0L,null);
        }
    }


    @RequestMapping("/getCountSetmeal")
    public Result getCountSetmeal(){
        try {
            Map<String,Object> map=iSetmealService.getCountSetmeal();
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }



}

