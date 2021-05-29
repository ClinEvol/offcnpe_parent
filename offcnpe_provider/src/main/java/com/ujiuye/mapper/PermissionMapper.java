package com.ujiuye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ujiuye.pojo.Permission;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> listByUserId(int user_id);
}
