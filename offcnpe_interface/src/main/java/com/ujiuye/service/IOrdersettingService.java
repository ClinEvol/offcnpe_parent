package com.ujiuye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ujiuye.pojo.Caldate;
import com.ujiuye.pojo.Ordersetting;

import java.util.List;

/**
 * <p>
 * 订单设置 服务类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
public interface IOrdersettingService extends IService<Ordersetting> {

    void saveList(List<Ordersetting> ordersettingList);

    List<Caldate> listOrdersetting(String date);

    void update(String date,Integer number);
}
