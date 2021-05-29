package com.ujiuye.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_menu")
public class Menu extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    @TableField("linkUrl")
    private String linkurl;

    private String path;

    private Integer priority;

    private String icon;

    private String description;

    @TableField("parentMenuId")
    private Integer parentmenuid;

    private Integer level;

    @TableLogic
    private Integer deleted;

}
