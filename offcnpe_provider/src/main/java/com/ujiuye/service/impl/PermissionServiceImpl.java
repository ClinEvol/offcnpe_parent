package com.ujiuye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ujiuye.mapper.PermissionMapper;
import com.ujiuye.pojo.Checkgroup;
import com.ujiuye.pojo.Permission;
import com.ujiuye.service.IPermissionService;
import com.ujiuye.utils.util.PageResult;
import com.ujiuye.utils.util.QueryPageBean;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public PageResult listPage(QueryPageBean queryPageBean) {
        Page<Permission> page=new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        String queryString=queryPageBean.getQueryString();

        QueryWrapper<Permission> queryWrapper=new QueryWrapper<>();
        if(queryString!=null && !queryString.equals("")){
            queryWrapper.like("name",queryString)
                    .or().like("keyword",queryString);
        }
        Page<Permission> permissionPage = permissionMapper.selectPage(page, queryWrapper);
        return new PageResult(permissionPage.getTotal(),permissionPage.getRecords());
    }

    @Override
    public List<Permission> listByUserId(int user_id) {
        return permissionMapper.listByUserId(user_id);
    }
}
