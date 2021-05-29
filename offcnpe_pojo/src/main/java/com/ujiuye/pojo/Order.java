package com.ujiuye.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_order")
public class Order extends Model {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员会id
     */
    private Integer memberId;

    /**
     * 约预日期
     */
    @TableField("orderDate")
    private LocalDate orderdate;

    /**
     * 约预类型 电话预约/微信预约
     */
    @TableField("orderType")
    private String ordertype;

    /**
     * 预约状态（是否到诊）
     */
    @TableField("orderStatus")
    private String orderstatus;

    /**
     * 餐套id
     */
    private Integer setmealId;

    @TableLogic
    private Integer deleted;
}
