package com.abhishek.springboot.jpa.crud.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJpaCrudExampleApplication {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		SpringApplication.run(SpringBootJpaCrudExampleApplication.class, args);

		Properties prop = new Properties();
		String fileName = "src/main/resources/application.properties";
		try (FileInputStream fis = new FileInputStream(fileName)) {
			prop.load(fis);
		}
		System.out.println(prop.getProperty("server.port"));
		System.out.println(prop.getProperty("spring.datasource.url"));
		System.out.println(prop.getProperty("spring.datasource.username"));
		System.out.println(prop.getProperty("spring.datasource.password"));
	}



}
