package com.ujiuye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ujiuye.pojo.Member;
import com.ujiuye.utils.util.MemberQueryPageBean;
import com.ujiuye.utils.util.PageResult;
import com.ujiuye.utils.util.QueryPageBean;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author encan
 * @since 2021-05-18
 */
public interface IMemberService extends IService<Member> {
    PageResult listPage(MemberQueryPageBean queryPageBean);
    List<Object> getAllYear();

    Map<String, Object> memberEcharts();

    //本日新增会员数
    Integer todayNewMember() throws Exception;
    //总会员数
    Integer totalMember();
    //本周新增会员数
    Integer thisWeekNewMember();
    //本月新增会员数
    Integer thisMonthNewMember();
}
