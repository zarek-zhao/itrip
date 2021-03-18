package com.zarek.itrip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhao
 * @date 2021/3/18 14:04
 */
@MapperScan("com.zarek.itrip.dao")
@SpringBootApplication
public class ItripApplicationStarter {
    public static void main(String[] args)
    {
        SpringApplication.run(ItripApplicationStarter.class,args);
    }
}
