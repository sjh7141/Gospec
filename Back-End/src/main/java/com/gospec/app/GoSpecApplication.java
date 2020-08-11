package com.gospec.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.gospec.property.FileUploadProperties;


@SpringBootApplication
@EnableConfigurationProperties({
	FileUploadProperties.class
	})
public class GoSpecApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoSpecApplication.class, args);
	}

}
    