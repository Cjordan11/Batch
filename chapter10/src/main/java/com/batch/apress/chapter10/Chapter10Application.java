package com.batch.apress.chapter10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chapter10Application {
	


	public static void main(String[] args) {
		String[] realArgs = {"customerUpdateFile=file:///C/:ApressBank/src/main/resources/customerUpdateFile.csv"};
		
		SpringApplication.run(Chapter10Application.class, realArgs);
	}
	

	



}
