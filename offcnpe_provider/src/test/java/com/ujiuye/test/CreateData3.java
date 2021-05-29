package com.ujiuye.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ujiuye.mapper.MemberMapper;
import com.ujiuye.mapper.SetmealMapper;
import com.ujiuye.mapper.UserMapper;
import com.ujiuye.pojo.Member;
import com.ujiuye.pojo.User;
import com.ujiuye.service.IMemberService;
import com.ujiuye.service.IOrderService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class CreateData3 {
   @Resource
   private IOrderService orderService;
   @Resource
   private IMemberService memberService;

   @Test
   void demo(){



   }



}
