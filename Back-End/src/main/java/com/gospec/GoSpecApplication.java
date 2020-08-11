package com.gospec;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.gospec.property.FileUploadProperties;


@SpringBootApplication
@ComponentScan
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties({
	FileUploadProperties.class
	})
public class GoSpecApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoSpecApplication.class, args);
//		new SpringApplicationBuilder(GoSpecApplication.class).build().run(args);
	}

}
    