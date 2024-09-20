package com.myplatform.myplatform;

import com.myplatform.myplatform.embedded.server.MyBootApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlatformApplication {

	public static void main(String[] args) {
		MyBootApplication.run(args);
	}

}
