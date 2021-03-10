package com.deutsche.storetrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.deutsche")
@EnableScheduling
public class StoretradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoretradeApplication.class, args);
	}

}
