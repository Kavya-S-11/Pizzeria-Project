package com.pizza.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UiServiceApplication.class, args);
	}

	
	 @Bean
	    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
	        return new HiddenHttpMethodFilter();
	    }

}
