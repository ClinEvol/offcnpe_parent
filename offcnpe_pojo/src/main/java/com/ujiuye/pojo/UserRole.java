package com.ujiuye.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 员工表与角色表的中间表，一个员工有多个角色
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_user_role")
@AllArgsConstructor
@NoArgsConstructor
public class UserRole extends Model {

    private static final long serialVersionUID = 1L;

      private Integer userId;

    private Integer roleId;


}
