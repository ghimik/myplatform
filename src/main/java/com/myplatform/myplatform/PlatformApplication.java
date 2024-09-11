package com.myplatform.myplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlatformApplication {

	public static void main(String[] args) {
		com.myplatform.myplatform.server.MyBootApplication.run(args);
	}

}
