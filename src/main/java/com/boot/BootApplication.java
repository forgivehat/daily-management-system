package com.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@MapperScan("com.boot.mapper")
@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
      ConfigurableApplicationContext run
              = SpringApplication.run(BootApplication.class, args);
    }
}
