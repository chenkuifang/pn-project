package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 自定义外置配置
 * 使用方法：1.直接使用@Value注解
 * 2.使用@Autowired注解注入配置类，再通过getXXX()方法获取，如:
 *
 * @author QuiFar
 * @Autowired private myConfiguration myConfig;
 * myConfig.getXXX();
 */
@Configuration
@ConfigurationProperties(prefix = "author")
@PropertySource(value = "classpath:config/my-defined.yml")
public class MyDefinedConfiguration {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static class Other {
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
