package com.luxury;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi  //swagger3.0
@SpringBootApplication
@MapperScan(value = "com.luxury.mapper")
public class LuxuryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuxuryApplication.class, args);
    }

}
