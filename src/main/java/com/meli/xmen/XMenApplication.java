package com.meli.xmen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class XMenApplication {

	public static void main(String[] args) {
		SpringApplication.run(XMenApplication.class, args);
	}

}
