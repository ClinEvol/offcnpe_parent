package com.ujiuye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ujiuye.mapper.CheckgroupCheckitemMapper;
import com.ujiuye.mapper.CheckgroupMapper;
import com.ujiuye.mapper.CheckitemMapper;
import com.ujiuye.pojo.Checkgroup;
import com.ujiuye.pojo.CheckgroupCheckitem;
import com.ujiuye.service.ICheckgroupService;
import com.ujiuye.utils.util.PageResult;
import com.ujiuye.utils.util.QueryPageBean;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 检查组 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class CheckgroupServiceImpl extends ServiceImpl<CheckgroupMapper, Checkgroup> implements ICheckgroupService {

    @Resource
    private CheckgroupMapper checkgroupMapper;
    @Resource
    private CheckgroupCheckitemMapper checkgroupCheckitemMapper;

    @Override
    public void save(Checkgroup checkgroup, Integer[] checkitemids) {
        //添加检查
        int insert = checkgroupMapper.insert(checkgroup);
        //获取刚刚被添加的那个检查组对象的id
        Integer checkgroupid = checkgroup.getId();
        //添加中间表
        for (Integer checkitemid : checkitemids) {
            CheckgroupCheckitem checkgroupCheckitem=new CheckgroupCheckitem();
            checkgroupCheckitem.setCheckgroupId(checkgroupid);
            checkgroupCheckitem.setCheckitemId(checkitemid);
            checkgroupCheckitemMapper.insert(checkgroupCheckitem);
        }
    }

    @Override
    public void update(Checkgroup checkgroup, Integer[] checkitemids) {
        checkgroupMapper.updateById(checkgroup);//修改检查组的信息

        //选删除中间的原来的数据
        checkgroupCheckitemMapper.delete(new QueryWrapper<CheckgroupCheckitem>().eq("checkgroup_id",checkgroup.getId()));

        //重新添加中间表
        for (Integer checkitemid : checkitemids) {
            CheckgroupCheckitem checkgroupCheckitem=new CheckgroupCheckitem();
            checkgroupCheckitem.setCheckgroupId(checkgroup.getId());
            checkgroupCheckitem.setCheckitemId(checkitemid);
            checkgroupCheckitemMapper.insert(checkgroupCheckitem);
        }
    }


    @Override
    public PageResult listPage(QueryPageBean queryPageBean){
        Page<Checkgroup> page=new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        String queryString=queryPageBean.getQueryString();
        QueryWrapper<Checkgroup> queryWrapper=new QueryWrapper<>();
        if(queryString!=null && !queryString.equals("")){
            queryWrapper.like("code",queryString)
                    .or().like("name",queryString)
                    .or().like("helpCode",queryString);
        }
        Page<Checkgroup> checkgroupPage = checkgroupMapper.selectPage(page, queryWrapper);

        return new PageResult(checkgroupPage.getTotal(),checkgroupPage.getRecords());
    }
}
