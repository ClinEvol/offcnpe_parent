package com.ujiuye.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ujiuye.mapper.MemberMapper;
import com.ujiuye.mapper.OrderMapper;
import com.ujiuye.mapper.UserMapper;
import com.ujiuye.pojo.Member;
import com.ujiuye.pojo.Order;
import com.ujiuye.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class CreateData2 {
   @Resource
   private OrderMapper orderMapper;

   @Test
   void create() {
      for (int i = 100; i < 500; i++) {
         Random random=new Random();
         Order order = new Order();
         order.setOrdertype("微信公众号预约");
         order.setMemberId(random.nextInt(20000)+100);
         int[] ids={12,24,13,27};

         int i1 = random.nextInt(ids.length);
         order.setSetmealId(ids[i1]);
         order.setOrderstatus("已到诊");
         order.setOrderdate(RegtimeUtils.getRegtime("2021-01-01", "2021-06-01").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
         orderMapper.insert(order);
         System.out.println(order);
         System.out.println("添加成功：" + i);
      }
   }

   @Test
   void update() {
      for (int i = 100; i < 500; i++) {
         Random random=new Random();
         int id=random.nextInt(20000)+100;
         Order order = orderMapper.selectById(id);
         if(order!=null){
            order.setOrderstatus("未到诊");
            orderMapper.updateById(order);
         }

         System.out.println("添加成功：" + i);
      }
   }


}
