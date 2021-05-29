package com.ujiuye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ujiuye.mapper.OrdersettingMapper;
import com.ujiuye.pojo.Caldate;
import com.ujiuye.pojo.Ordersetting;

import com.ujiuye.service.IOrdersettingService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单设置 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class OrdersettingServiceImpl extends ServiceImpl<OrdersettingMapper, Ordersetting> implements IOrdersettingService {
    @Resource
    private OrdersettingMapper ordersettingMapper;

    @Override
    public void saveList(List<Ordersetting> ordersettingList) {

        for (Ordersetting ordersetting : ordersettingList) {
            QueryWrapper<Ordersetting> queryWrapper=new QueryWrapper<>();
            //先一下数据库有没有这个日期
            queryWrapper.eq("orderDate", ordersetting.getOrderdate());
            Ordersetting sysOrdersetting = ordersettingMapper.selectOne(queryWrapper);
            if(sysOrdersetting==null){//没有这个日期
                ordersettingMapper.insert(ordersetting);
            }else{//存这个日期
                sysOrdersetting.setNumber(ordersetting.getNumber());//修改新的可预约人数
                ordersettingMapper.updateById(sysOrdersetting);
            }

        }
    }

    @Override
    public List<Caldate> listOrdersetting(String date) {//date =2021/05  查5月份   2021-05-01  2021-05-31
        String beginTime=date+"-01";   //    2021-05-01
        String endTime=date+"-31";
        QueryWrapper<Ordersetting> queryWrapper=new QueryWrapper<>();
        queryWrapper.between("orderDate",beginTime,endTime);
        List<Ordersetting> ordersettings = ordersettingMapper.selectList(queryWrapper);
        List<Caldate> list=new ArrayList<>();
        //把ordersettings的数据转换为前台需要的格式
        if(ordersettings!=null && ordersettings.size()>0){
            for (Ordersetting ordersetting : ordersettings) {
                int day = ordersetting.getOrderdate().getDayOfMonth();
                Integer number = ordersetting.getNumber();
                Integer reservations = ordersetting.getReservations();
                list.add(new Caldate(day,number,reservations));
            }
        }
        return list;
    }

    @Override
    public void update(String date, Integer number) {
        QueryWrapper<Ordersetting> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("orderDate",date);
        Ordersetting ordersetting = ordersettingMapper.selectOne(queryWrapper);
        if(ordersetting!=null){////有这个日期
            ordersetting.setNumber(number);
            ordersettingMapper.updateById(ordersetting);
        }else{//没有这个日期  新增一个
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date2 = LocalDate.parse(date, fmt);
            Ordersetting ordersetting1=new Ordersetting(0,date2,number,0);
            ordersettingMapper.insert(ordersetting1);
        }
    }
}
