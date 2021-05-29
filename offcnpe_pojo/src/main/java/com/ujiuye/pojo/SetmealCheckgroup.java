package com.ujiuye.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 套餐与检查组，一个套餐有多个检查组
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_setmeal_checkgroup")
public class SetmealCheckgroup extends Model {

    private static final long serialVersionUID = 1L;

      private Integer setmealId;

    private Integer checkgroupId;


}
