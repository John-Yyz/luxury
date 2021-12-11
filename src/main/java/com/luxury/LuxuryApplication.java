package com.luxury;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/*@EntityScan(basePackages = "com.luxury.model")
@MapperScan(value = "com.luxury.mapper")
@ComponentScan({ "com.luxury.mapper"})*/
@SpringBootApplication(scanBasePackages = {"com.luxury","com.luxury.config"})
@ComponentScan(basePackages = {"com.luxury.config","com.luxury.filter"})//根据自己需要填写包名
@EnableSwagger2
public class LuxuryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuxuryApplication.class, args);
    }

}
