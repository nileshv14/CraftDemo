package com.example.CraftDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.example.CraftDemo")
@SpringBootApplication
public class CraftDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CraftDemoApplication.class, args);
	}

}
