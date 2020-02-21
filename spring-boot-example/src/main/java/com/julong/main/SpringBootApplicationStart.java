package com.julong.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * springboot 启动文件
 * @author julong
 * @date 2020年1月16日 下午5:25:52
 * @desc SpringBootApplication same as @Configuration @EnableAutoConfiguration @ComponentScan
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages="com.julong")
public class SpringBootApplicationStart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(SpringBootApplicationStart.class, args);
	}

}
