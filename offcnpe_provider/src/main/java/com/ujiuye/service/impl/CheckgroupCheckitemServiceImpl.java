package com.ujiuye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ujiuye.mapper.CheckgroupCheckitemMapper;
import com.ujiuye.pojo.CheckgroupCheckitem;
import com.ujiuye.service.ICheckgroupCheckitemService;
import com.ujiuye.utils.util.MessageConstant;
import com.ujiuye.utils.util.Result;
import org.apache.dubbo.config.annotation.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 栓查项与检查组的中间表，一个检查组有多个检查项 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class CheckgroupCheckitemServiceImpl extends ServiceImpl<CheckgroupCheckitemMapper, CheckgroupCheckitem> implements ICheckgroupCheckitemService {

    @Resource
    private CheckgroupCheckitemMapper checkgroupCheckitemMapper;

    @Override
    public Result listByCheckgroupId(Integer checkgroupId) {
        QueryWrapper<CheckgroupCheckitem> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("checkgroup_id",checkgroupId);
        List<CheckgroupCheckitem> selectList = checkgroupCheckitemMapper.selectList(queryWrapper);
        //当前selectList     [{15,90},{15,91},{15,92}]
        //把集合变为前台支持数据格式   checkitemIds=[90,91,92]
        List<Integer> checkitemIds=new ArrayList();
        for (CheckgroupCheckitem checkgroupCheckitem : selectList) {
            checkitemIds.add(checkgroupCheckitem.getCheckitemId());
        }
        return new Result(true, MessageConstant.QUERY_CHECKGROUPCHECKITEM_SUCCESS,checkitemIds);
    }
}
