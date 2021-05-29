package com.ujiuye.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 检查组
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_checkgroup")
public class Checkgroup extends Model {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private String name;

    @TableField("helpCode")
    private String helpcode;

    private String sex;

    private String remark;

    private String attention;

    @TableLogic
    private Integer deleted;


    @TableField(exist = false)
    private List<Checkitem> checkitemList;//一个检查组包含多个检查项
}
