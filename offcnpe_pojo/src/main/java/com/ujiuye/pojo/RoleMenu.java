package com.ujiuye.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 角色表与菜单表的中间表，一个角色有多个菜单
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_role_menu")
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenu extends Model {

    private static final long serialVersionUID = 1L;

      private Integer roleId;

    private Integer menuId;


}
