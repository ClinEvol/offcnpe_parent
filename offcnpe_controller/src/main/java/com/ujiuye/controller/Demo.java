package com.ujiuye.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Demo {
    public static void main(String[] args) {
        String date1 = "2019-06-13";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date2 = LocalDate.parse(date1, fmt);
        System.out.println(date1);
        System.out.println(date2);
    }
}
