package com.ujiuye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ujiuye.mapper.MemberMapper;
import com.ujiuye.mapper.OrderMapper;
import com.ujiuye.mapper.OrdersettingMapper;
import com.ujiuye.pojo.Member;
import com.ujiuye.pojo.Order;
import com.ujiuye.pojo.Ordersetting;
import com.ujiuye.service.IOrderService;
import com.ujiuye.utils.util.DateUtils;
import com.ujiuye.utils.util.MessageConstant;
import com.ujiuye.utils.util.Result;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrdersettingMapper ordersettingMapper;
    @Resource
    private MemberMapper memberMapper;

    /*
    1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行 预约
2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约 则无法完成再次预约
4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注 册并进行预约
5、预约成功，更新当日的已预约人数
     */
    @Override
    public Result submit(Map<String, Object> map) throws Exception {
        //1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行 预约
        QueryWrapper<Ordersetting> osQueryWrapper=new QueryWrapper<>();
        String orderDate =(String) map.get("orderDate");
        osQueryWrapper.eq("orderDate", DateUtils.parseString2Date(orderDate));
        Ordersetting ordersetting = ordersettingMapper.selectOne(osQueryWrapper);
        if(ordersetting==null){//没有进行了预约设置
            return  new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        //进行了预约设置
        //2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
        if(ordersetting.getNumber()<=ordersetting.getReservations()){//约满了
            return  new Result(false, MessageConstant.ORDER_FULL);
        }
        //没有约满
        //3、检查是不是会员
        QueryWrapper<Member> memberQueryWrapper=new QueryWrapper<>();
        memberQueryWrapper.eq("phoneNumber",(String)map.get("telephone"));
        Member member = memberMapper.selectOne(memberQueryWrapper);
        if(member==null){//不是会员则自动完成注
            //不是会员则自动完成注 册并进行预约    创建会员
            member=new Member();
            member.setPhonenumber((String)map.get("telephone"));
            member.setSex((String)map.get("sex"));
            member.setIdcard((String)map.get("idCard"));
            member.setName((String)map.get("name"));
            member.setRegtime(LocalDate.now());
            member.setPassword("123456");
            int insert = memberMapper.insert(member);
        }
        //是会员或者已经完注册
        //4、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约 则无法完成再次预约
        QueryWrapper<Order> orderQueryWrapper=new QueryWrapper<>();
        orderQueryWrapper.eq("member_id",member.getId());
        orderQueryWrapper.eq("orderDate",DateUtils.parseString2Date(orderDate));
        orderQueryWrapper.eq("setmeal_id",map.get("setmealId"));
        Order order = orderMapper.selectOne(orderQueryWrapper);
        if(order!=null){//已经预约过了
            return  new Result(false, MessageConstant.HAS_ORDERED);
        }
        //没有预约过，实现预约   创建Order对象
        Order inOrder=new Order();
        inOrder.setMemberId(member.getId());
        inOrder.setOrdertype("微信公众号预约");
        inOrder.setOrderdate(LocalDate.parse(orderDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        inOrder.setSetmealId(Integer.parseInt((String)map.get("setmealId")));
        orderMapper.insert(inOrder);
        //预约成功，更新当日的已预约人数
        ordersetting.setReservations(ordersetting.getReservations()+1);
        ordersettingMapper.updateById(ordersetting);
        return new Result(true,MessageConstant.ORDER_SUCCESS);


    }

    @Override
    public Integer todayOrderNumber() throws Exception {
        QueryWrapper<Order> orderQueryWrapper=new QueryWrapper<>();
        String string = DateUtils.parseDate2String(DateUtils.getToday());
        orderQueryWrapper.eq("orderDate",string);
        return orderMapper.selectCount(orderQueryWrapper);
    }

    @Override
    public Integer todayVisitsNumber() throws Exception {
        QueryWrapper<Order> orderQueryWrapper=new QueryWrapper<>();
        String string = DateUtils.parseDate2String(DateUtils.getToday());
        orderQueryWrapper.eq("orderDate",string);
        orderQueryWrapper.eq("orderStatus","已到诊");
        return orderMapper.selectCount(orderQueryWrapper);
    }

    @Override
    public Integer thisWeekOrderNumber() {
        QueryWrapper<Order> orderQueryWrapper=new QueryWrapper<>();
        Date monday = DateUtils.getThisWeekMonday(DateUtils.getToday());//获取本周一时间
        orderQueryWrapper.between("orderDate",monday,DateUtils.getToday());
        return orderMapper.selectCount(orderQueryWrapper);
    }

    @Override
    public Integer thisWeekVisitsNumber() {
        QueryWrapper<Order> orderQueryWrapper=new QueryWrapper<>();
        Date monday = DateUtils.getThisWeekMonday(DateUtils.getToday());//获取本周一时间
        orderQueryWrapper.between("orderDate",monday,DateUtils.getToday());
        orderQueryWrapper.eq("orderStatus","已到诊");
        return orderMapper.selectCount(orderQueryWrapper);
    }

    @Override
    public Integer thisMonthOrderNumber() {
        QueryWrapper<Order> orderQueryWrapper=new QueryWrapper<>();
        Date one = DateUtils.getFirstDay4ThisMonth();//获取本月1号时间
        orderQueryWrapper.between("orderDate",one,DateUtils.getToday());
        return orderMapper.selectCount(orderQueryWrapper);
    }

    @Override
    public Integer thisMonthVisitsNumber() {
        QueryWrapper<Order> orderQueryWrapper=new QueryWrapper<>();
        Date one = DateUtils.getFirstDay4ThisMonth();//获取本月1号时间
        orderQueryWrapper.between("orderDate",one,DateUtils.getToday());
        orderQueryWrapper.eq("orderStatus","已到诊");
        return orderMapper.selectCount(orderQueryWrapper);
    }

    @Override
    public List<Map<String, Object>> hotSetmeal() {
        return orderMapper.hotSetmeal();
    }
}
