package com.ujiuye.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegtimeUtils {
    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            System.out.println(getRegtime("2020-01-01","2021-12-30"));
        }
    }

    /**
     *在区间生成随机时间
     */
    private static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }
    /**
     *得到初始时间
     */
    public static Date getRegtime(String beginDate,String endDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if(start.getTime() >= end.getTime()){
                return null;
            }
            long date = random(start.getTime(),end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
