package com.upgrad.mba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@SpringBootApplication
public class MovieBookingApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MovieBookingApplication.class, args);
		DataSource dataSource = context.getBean(DataSource.class);

		System.out.println("**********************************");
		Resource resourceDev = new ClassPathResource("application-dev.properties");
		System.out.println(resourceDev.exists());
		System.out.println("**********************************");
	}

}
