package com.intermap.content.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:content-audit-application.xml")
@SpringBootApplication
public class GoodviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodviewApplication.class, args);
	}

}
