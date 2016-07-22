package com.dmh.reflex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class ReplaysApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ReplaysApplication.class, args);
	}

	// Used when deploying to a standalone servlet container
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ReplaysApplication.class);
	}
}
