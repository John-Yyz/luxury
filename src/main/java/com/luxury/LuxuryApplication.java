package com.luxury;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication(scanBasePackages = {"com.luxury.*"})
@MapperScan("com.luxury.mapper")
@EnableSwagger2
public class LuxuryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuxuryApplication.class, args);
    }

}
