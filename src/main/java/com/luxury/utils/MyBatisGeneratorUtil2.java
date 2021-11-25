package com.luxury.utils;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/20 19:31
 */
public class MyBatisGeneratorUtil2 {

    public static void main(String[] args) {
        MyBatisGeneratorUtil2 generator = new MyBatisGeneratorUtil2();
        System.out.println(System.getProperty("user.dir"));
        System.out.println(generator.getClass().getResource("/").getPath());
        generator.run();
    }

    public void run() {
        try {
            InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("mybatis/generatorConfig2.xml");
            List<String> warnings = new ArrayList<>();
            ConfigurationParser parser = new ConfigurationParser(warnings);
            Configuration config = parser.parseConfiguration(resourceAsStream);
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);
            for (String warning : warnings) {
                System.err.println(">" + warning);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
