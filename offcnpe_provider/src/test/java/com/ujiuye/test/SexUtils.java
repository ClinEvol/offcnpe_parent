package com.ujiuye.test;

import java.util.Random;

public class SexUtils {
    public static String getSex(){
        Random random=new Random();
        if(random.nextInt(10)%2==0){
            return "男";
        }else {
            return "女";
        }
    }
}
