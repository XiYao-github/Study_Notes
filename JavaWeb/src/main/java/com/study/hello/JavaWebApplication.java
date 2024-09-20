package com.study.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "hello.servlet")
public class JavaWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(JavaWebApplication.class, args);
	}
}
