package com.ujiuye.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ujiuye.mapper.MemberMapper;
import com.ujiuye.mapper.UserMapper;
import com.ujiuye.pojo.Member;
import com.ujiuye.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class CreateData {
   @Resource
   private MemberMapper memberMapper;
   @Resource
   private UserMapper userMapper;

   @Test
   void create(){
      for (int i=0;i<5000;i++) {
         Member member=new Member();
         member.setBirthday(RegtimeUtils.getRegtime("1930-01-01","2020-01-01").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
         member.setEmail(EmailUtils.getEmail());
         member.setRegtime(RegtimeUtils.getRegtime("2013-01-01","2018-01-01").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
         member.setIdcard(new IdCardGenerator().generate());
         member.setPassword(PasswordUtils.getPassword());
         member.setSex(SexUtils.getSex());
         member.setPhonenumber(PhoneNumberUtils.createPhoneNumber());
         member.setName(NameUtils.getName());
         member.setFilenumber("default.jpg");
         try {
            memberMapper.insert(member);
         } catch (Exception e) {
            e.printStackTrace();
         }
         System.out.println("添加成功："+i);
   }


   }

   @Test
   void create2(){
      for (int i=0;i<3;i++) {
         User user=new User();
         user.setGender(SexUtils.getSex());
         user.setBirthday(RegtimeUtils.getRegtime("1980-01-01","2000-01-01").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
         user.setTelephone(PhoneNumberUtils.createPhoneNumber());
         user.setPassword("$2a$10$.VyqsdVCwILy8HmoKPO8ZuCPLmwmZh.dYZrFQP2E76hlyouM9Vod6");
         String username = PinYinUtils.pinyinAll(NameUtils.getName());
         user.setUsername(username);
         //userMapper.insert(user);
         System.out.println(user);
      }


   }



   @Test
   void check(){
      List<Integer> count=new ArrayList<>();
      for (int i=2003;i<=2021;i++) {
         QueryWrapper<Member> queryWrapper=new QueryWrapper<>();
         String begin=i+"/01/01";
         String end=i+"/12/31";
         queryWrapper.between("regTime",begin,end);
         Integer integer = memberMapper.selectCount(queryWrapper);
         count.add(integer);
      }

      System.out.println(count);
   }

   @Test
   void check2(){
      List<Integer> count=new ArrayList<>();
      for (int i=01;i<=05;i++) {
         QueryWrapper<Member> queryWrapper=new QueryWrapper<>();
         String begin="2021/"+i+"/01";
         String end="2021/"+i+"/31";
         queryWrapper.between("regTime",begin,end);
         Integer integer = memberMapper.selectCount(queryWrapper);
         count.add(integer);
      }

      System.out.println(count);
   }
}
