package com.ujiuye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.PageList;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ujiuye.mapper.*;
import com.ujiuye.pojo.*;
import com.ujiuye.service.ISetmealService;
import com.ujiuye.utils.util.PageResult;
import com.ujiuye.utils.util.QueryPageBean;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.*;


/**
 * <p>
 * 套餐 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements ISetmealService {
    @Resource
    private SetmealMapper setmealMapper;
    @Resource
    private SetmealCheckgroupMapper setmealCheckgroupMapper;
    @Resource
    private CheckgroupMapper checkgroupMapper;
    @Resource
    private CheckgroupCheckitemMapper checkgroupCheckitemMapper;
    @Resource
    private CheckitemMapper checkitemMapper;

    @Override
    public Setmeal getInfo(Integer id) {
        Setmeal setmeal = setmealMapper.selectById(id);
        //一个套餐包含多个检查组
        //1、查询中间表看一下这个套餐有什么检查组   SELECT * FROM t_setmeal_checkgroup WHERE setmeal_id=12
        QueryWrapper<SetmealCheckgroup> scQueryWrapper=new QueryWrapper<>();
        scQueryWrapper.eq("setmeal_id",setmeal.getId());
        List<SetmealCheckgroup> setmealCheckgroupList = setmealCheckgroupMapper.selectList(scQueryWrapper);
        if(setmealCheckgroupList==null || setmealCheckgroupList.size()==0){//没有查到对应的检查组   一般不会发生
            return setmeal;
        }

        List<Checkgroup> checkgroupList=new ArrayList<>();//用于保存该套餐下有什么检查
        //2、查到对应的检查组     查询每个检组的信息   SELECT * FROM t_checkgroup WHERE id=5
        for (SetmealCheckgroup setmealCheckgroup : setmealCheckgroupList) {
            Checkgroup checkgroup = checkgroupMapper.getById(setmealCheckgroup.getCheckgroupId());
            //查检项这个个检查组有什么检查项   SELECT * FROM t_checkgroup_checkitem WHERE checkgroup_id=5
            QueryWrapper<CheckgroupCheckitem> ccQueryWrapper=new QueryWrapper<>();
            ccQueryWrapper.eq("checkgroup_id",setmealCheckgroup.getCheckgroupId());
            List<CheckgroupCheckitem> checkgroupCheckitemList = checkgroupCheckitemMapper.selectList(ccQueryWrapper);

            if(checkgroupCheckitemList!=null && checkgroupCheckitemList.size()>0){//有查到对应的检查项
                List<Checkitem> checkitemList=new ArrayList<>();////用于保存该检查组下有什么检查项目
                //查询检项信息
                for (CheckgroupCheckitem checkgroupCheckitem : checkgroupCheckitemList) {
                    //SELECT * FROM t_checkitem WHERE id=28
                    Checkitem checkitem = checkitemMapper.getById(checkgroupCheckitem.getCheckitemId());
                    checkitemList.add(checkitem);
                }
                //把集合的数据设置到Checkgroup对象中
                checkgroup.setCheckitemList(checkitemList);
            }
            checkgroupList.add(checkgroup);
        }
        ////把集合的数据设置到setmeal对象中
        setmeal.setCheckgroupList(checkgroupList);
        // 一个检查组包含多个检查项   这些都要查出来
        return setmeal;
    }




    @Override
    public Map<String, Object> getCountSetmeal() {
        List<Map<String, Object>> countSetmeal = setmealMapper.getCountSetmeal();

        List<String> names=new ArrayList<>();
        for (Map<String, Object> stringObjectMap : countSetmeal) {
            names.add(stringObjectMap.get("name").toString());
        }

        Map<String, Object> map=new HashMap<>();
        map.put("setmealCount",countSetmeal);
        map.put("setmealNames",names);
        return map;
    }





    @Override
    public void save(Setmeal setmeal, Integer[] checkgroupIds) {

        setmealMapper.insert(setmeal);

        for (Integer checkgroupId : checkgroupIds) {
            SetmealCheckgroup setmealCheckgroup=new SetmealCheckgroup();
            setmealCheckgroup.setSetmealId(setmeal.getId());
            setmealCheckgroup.setCheckgroupId(checkgroupId);
            setmealCheckgroupMapper.insert(setmealCheckgroup);
        }

    }

    @Override
    public PageResult listPage(QueryPageBean queryPageBean) {
        Page<Setmeal> page=new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        String queryString=queryPageBean.getQueryString();
        QueryWrapper<Setmeal> queryWrapper=new QueryWrapper<>();
        if(queryString!=null && !queryString.equals("")){
            queryWrapper.like("code",queryString)
                    .or().like("name",queryString)
                    .or().like("helpCode",queryString);
        }
        Page<Setmeal> setmealPage = setmealMapper.selectPage(page, queryWrapper);

        return new PageResult(setmealPage.getTotal(),setmealPage.getRecords());
    }



}
