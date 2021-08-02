package com.example.dnschecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DnscheckerApplication {

	public static void main(String[] args) {

		SpringApplication.run(DnscheckerApplication.class, args);
	}

}
