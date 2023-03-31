package com.book.projectps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class FreeArtistsApp {

	public static void main(String[] args) {
		SpringApplication.run(FreeArtistsApp.class, args);
	}

}
