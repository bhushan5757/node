package com.NodeTester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class NodeTester1Application {

	public static void main(String[] args) {
		SpringApplication.run(NodeTester1Application.class, args);
	}

}
