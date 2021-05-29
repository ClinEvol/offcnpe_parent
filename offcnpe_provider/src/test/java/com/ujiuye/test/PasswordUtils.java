package com.ujiuye.test;

import java.util.Random;

public class PasswordUtils {


    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            System.out.println(getPassword());
        }
    }
    /**
     *生成密码
     */
    public static String getPassword(){
        Random random=new Random();
        String pwd="";
        //密码为6-10位
        for(int i=0;i<random.nextInt(5)+6;i++){
            pwd+=random.nextInt(10);
        }
        return pwd;
    }
}
