package com.ujiuye.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class PhoneNumberUtils {

    //移动电话号码前三位
    private static final String[] YD={"134","135","136","137","138","139","150","151","152","157","158","159","180","181","182","183","184","185","174","192","178"};
    //电信号码前三位
    private static final String[] DX = {"133","149","153","173","177","180","181","189","199"};
    //联通号码前三位
    private static final String[] LT ={"130","131","132","145","155","156","166","171","175","176","185","186","166"};

    public static String createPhoneNumber(){
        //定义随机数
        Random random = new Random();
        //随机号码的第4位数字
        int i1 = random.nextInt(10);
        //随机号码的第5位数字
        int i2 = random.nextInt(10);
        //随机号码的第6位数字
        int i3 = random.nextInt(10);
        //随机号码的第7位数字
        int i4 = random.nextInt(10);
        //随机号码的第8位数字
        int i5 = random.nextInt(10);
        //随机号码的第9位数字
        int i6 = random.nextInt(10);
        //随机号码的第10位数字
        int i7 = random.nextInt(10);
        //随机号码的第11位数字
        int i8 = random.nextInt(10);
        StringBuilder stringBuilder = new StringBuilder();
        int type = random.nextInt(3);
        if(type==1){
            //从移动号码规则里面随机一个号码前三位
            int i = random.nextInt(YD.length);
            stringBuilder.append(YD[i]).append(i1).append(i2).append(i3).append(i4).append(i5).append(i6).append(i7).append(i8);
        }else if(type==2){
            //从移动号码规则里面随机一个号码前三位
            int w = random.nextInt(DX.length);
            stringBuilder.append(DX[w]).append(i1).append(i2).append(i3).append(i4).append(i5).append(i6).append(i7).append(i8);
        }else{
            //从移动号码规则里面随机一个号码前三位
            int n = random.nextInt(LT.length);
            stringBuilder.append(LT[n]).append(i1).append(i2).append(i3).append(i4).append(i5).append(i6).append(i7).append(i8);
        }
        return stringBuilder.toString();
    }
}
