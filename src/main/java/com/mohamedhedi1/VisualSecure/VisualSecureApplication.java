package com.mohamedhedi1.VisualSecure;

import com.mohamedhedi1.VisualSecure.auth.AuthenticationService;
import com.mohamedhedi1.VisualSecure.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class VisualSecureApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisualSecureApplication.class, args);
	}



}
