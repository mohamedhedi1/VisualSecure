package com.mohamedhedi1.VisualSecure;

import com.mohamedhedi1.VisualSecure.auth.AuthenticationService;
import com.mohamedhedi1.VisualSecure.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.mohamedhedi1.VisualSecure.user.Role.ADMIN;

@SpringBootApplication
public class VisualSecureApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisualSecureApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());



		};
	}

}
