package com.ujiuye;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ujiuye.mapper")
public class PeServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(PeServiceProviderApplication.class);
    }
}
