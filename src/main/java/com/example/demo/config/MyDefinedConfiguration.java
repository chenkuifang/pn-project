package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 自定义外置配置(第三方配置文件获取推荐使用@ConfigurationProperties)
 * 使用方法：1.直接使用@Value注解
 * 2.使用@Autowired注解注入配置类，再通过getXXX()方法获取，如:
 * 该方式是类型安全的
 *
 * @author QuiFar
 * @Autowired private myConfiguration myConfig;
 * myConfig.getXXX();
 * <p>
 * 注意：YAML files cannot be loaded by using the @PropertySource annotation.
 * So, in the case that you need to load values that way, you need to use a properties file.
 * 需要注意的是，如果需要@PropertySource注解，那就必须使用properties文件，不能使用yml文件
 */
@Configuration
@ConfigurationProperties(prefix = "author")
@PropertySource(value = "classpath:config/my-defined.properties", encoding = "GBK")
public class MyDefinedConfiguration {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
