
package com.ujiuye.controller;


import com.ujiuye.pojo.CheckgroupCheckitem;
import com.ujiuye.service.ICheckgroupCheckitemService;
import com.ujiuye.utils.util.MessageConstant;
import com.ujiuye.utils.util.Result;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 栓查项与检查组的中间表，一个检查组有多个检查项 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/checkgroup-checkitem")
public class CheckgroupCheckitemController {
    @Reference
    private ICheckgroupCheckitemService iCheckgroupCheckitemService;

    @GetMapping("/{checkgroupId}")
    public Result getListByCheckgroupId(@PathVariable("checkgroupId") Integer checkgroupId){
        try {
            Result result = iCheckgroupCheckitemService.listByCheckgroupId(checkgroupId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUPCHECKITEM_FAIL);
        }
    }
}

