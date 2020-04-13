package com.barley.flow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot 入口
 * 
 * @author peculiar.1@163.com
 * @version $ID: FlowSpringBootApplication.java, V1.0.0 2020年4月12日 上午10:07:08 $
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class FlowSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowSpringBootApplication.class, args);
    }
}
