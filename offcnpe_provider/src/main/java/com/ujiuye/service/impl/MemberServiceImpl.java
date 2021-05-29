package com.ujiuye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ujiuye.mapper.MemberMapper;
import com.ujiuye.pojo.Member;
import com.ujiuye.service.IMemberService;
import com.ujiuye.utils.util.DateUtils;
import com.ujiuye.utils.util.MemberQueryPageBean;
import com.ujiuye.utils.util.PageResult;
import com.ujiuye.utils.util.QueryPageBean;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author encan
 * @since 2021-05-18
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {
    @Resource
    private MemberMapper memberMapper;




    @Override
    public PageResult listPage(MemberQueryPageBean queryPageBean) {
        Page<Member> page = new Page<>(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        String queryString = queryPageBean.getQueryString();
        if (queryString != null && !queryString.equals("")) {
            queryWrapper.and(i->i.like("name", queryString).or().like("phoneNumber", queryString));
        }
        //查性别
        if(!queryPageBean.getSex().equals("不限")){
            queryWrapper.eq("sex",queryPageBean.getSex());
        }
        //查注册时间
        if(!queryPageBean.getReg().equals("不限")){  //前台传  2003
            String beginTime=queryPageBean.getReg()+"-01-01";   //2003--01-01
            String endTime=queryPageBean.getReg()+"-12-31";   //2003-12-31
            queryWrapper.between("regTime",beginTime,endTime);
        }
        //查年龄
        String ageStr=queryPageBean.getAge();
        if(!ageStr.equals("不限")){
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month=calendar.get(Calendar.MONTH) + 1;
            int day=calendar.get(Calendar.DAY_OF_MONTH);
            if(ageStr.contains("-")){
                String[] split = ageStr.split("-");//前台传的字符串：20-29    分割后：[20,29]
                String endTime=(year-Integer.parseInt(split[0]))+"-"+month+"-"+day;//当前年份减去20
                String beginTime=(year-Integer.parseInt(split[1]))+"-"+month+"-"+day;//当前年份减去29
                queryWrapper.between("birthday",beginTime,endTime);
            }else{//60以上
                queryWrapper.lt("birthday",(year-60)+"-"+month+"-"+day);
            }
        }
        Page<Member> memberPage = memberMapper.selectPage(page, queryWrapper);
        return new PageResult(memberPage.getTotal(), memberPage.getRecords());

    }

    @Override
    public List<Object> getAllYear() {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(" MIN(regTime) AS regTime ");
        List<Member> members = memberMapper.selectList(queryWrapper);//查询数据库中最早的注册时间

        int sysYear = members.get(0).getRegtime().getYear();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);//求当前的时间

        List<Object> list=new ArrayList<>();
        list.add("不限");
        //生成列表
        for(int i=sysYear;i<=currentYear;i++){
            list.add(i);
        }
        return list;
    }

    //过去一年时间内每个月的会员总数据量
    @Override
    public Map<String, Object> memberEcharts() {

        List<String> months=new ArrayList<>();//保存所有月份
        List<Integer> memberCount=new ArrayList<>();//保存月份的会员数量

        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        //把时间往前推12个月
        calendar.add(Calendar.MONTH,-12);   //2020-05
        for(int i=0;i<12;i++){
            calendar.add(Calendar.MONTH,1);   //2020-06   2020-07   2020-08
            Date time = calendar.getTime();//获取日历的时间
            //格式时间
            String format = new SimpleDateFormat("yyyy-MM").format(time);//2020-06
            months.add(format);
        }
        //循环完毕完  months:[2020-06,2020-07,2020-08,2020-09......2021-05]
        //查询集合中的所有月份的会员总数
        for (String month : months) {
            QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
            queryWrapper.le("regTime",month+"31");
            Integer count = memberMapper.selectCount(queryWrapper);
            memberCount.add(count);//把查到的数量保到memberCount
        }

        //把months与memberCount放到map集合中一起返回
        Map<String, Object> map=new HashMap<>();
        map.put("months",months);
        map.put("memberCount",memberCount);
        return map;
    }

    @Override
    public Integer todayNewMember() throws Exception {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        String string = DateUtils.parseDate2String(DateUtils.getToday());
        queryWrapper.eq("regTime",string);
        return memberMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer totalMember() {
        return memberMapper.selectCount(null);
    }

    @Override
    public Integer thisWeekNewMember() {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        Date monday = DateUtils.getThisWeekMonday(DateUtils.getToday());//获取本周一时间
        queryWrapper.between("regTime", monday,DateUtils.getToday());
        return memberMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer thisMonthNewMember() {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        Date one = DateUtils.getFirstDay4ThisMonth();//获取本月1号时间
        queryWrapper.between("regTime", one,DateUtils.getToday());
        return memberMapper.selectCount(queryWrapper);
    }
}
