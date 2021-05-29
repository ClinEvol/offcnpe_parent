package com.ujiuye.test;

import java.util.Random;

public class EmailUtils {

    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            System.out.println(getEmail());
        }
    }
    /**
     * 生成邮箱
     */
    public static String getEmail(){
        Random random = new Random();
        String preffix="";
        int n=random.nextInt(10);
        if(n%4==0){
            preffix=PinYinUtils.pinyinAll(NameUtils.getName());
        }else{
            preffix=PinYinUtils.pinyinHead(NameUtils.getName());
        }
        String number=String.valueOf(Math.random()).substring(3,3+random.nextInt(8));

        int i=random.nextInt(3);
        String[] suffixs={"@gmail.com","@yahoo.com","@msn.com","@hotmail.com","@aol.com","@ask.com","@live.com","@qq.com","@0355.net","@163.com","@163.net","@263.net","@3721.net","@yeah.ne","@googlemail.com","@mail.com"};
        String suffix=suffixs[random.nextInt(suffixs.length)];
        return preffix+number+suffix;
    }
}
