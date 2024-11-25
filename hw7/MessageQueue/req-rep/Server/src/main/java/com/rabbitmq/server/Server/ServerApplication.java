package com.rabbitmq.server.Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		System.out.println("Started MQserver");
		SpringApplication.run(ServerApplication.class, args);
	}

}
