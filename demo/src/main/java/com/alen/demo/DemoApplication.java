package com.alen.demo;

import com.alen.demo.security.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(RoleRepository repo, UserRepository userRepo) {
		return args -> {
			repo.save(new Role(1, "ADMIN"));
			repo.save(new Role(2, "USER"));
		};
	}

}
