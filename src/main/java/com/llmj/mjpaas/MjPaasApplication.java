package com.llmj.mjpaas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.llmj.mjpaas.mapper")
public class MjPaasApplication {

    public static void main(String[] args) {
        SpringApplication.run(MjPaasApplication.class, args);
    }

}
