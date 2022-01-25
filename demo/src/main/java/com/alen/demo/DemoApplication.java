package com.alen.demo;


import com.alen.demo.security.RegistrationForm;
import com.alen.demo.security.User;
import com.alen.demo.security.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class DemoApplication  {
	// @Autowired
	// UserRepository userRepo;
	// @Autowired
	// RoleRepository repo;
	// @Autowired
	// RegistrationForm form;
	// @Autowired
	// PasswordEncoder passwordEncoder;
	// @Autowired
	// public void run(String...args) throws Exception{
	// 	repo.save(new Role(1,"ADMIN"));
	// 	repo.save(new Role(2,"USER"));
	// 	if(userRepo.findByRole("ADMIN")==null){
	// 		form.setEmail("admin@universty");
	// 		form.setRole("ADMIN");
	// 		form.setPhone("091111");
	// 		form.setUsername("admin");
	// 		form.setPassword("1234");
	// 		User myUser=form.toUsering(passwordEncoder);
	// 		Role userRole=repo.findByName("ADMIN");
	// 		myUser.addRole(userRole);
	
	// 		userRepo.save(myUser);

	// 	}
	

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner dataLoader( RoleRepository repo,UserRepository userRepo){
		return args->{
			repo.save(new Role(1,"ADMIN"));
			repo.save(new Role(2,"USER"));
			// if(userRepo.findByRole("ADMIN")==null){
			// 	form.setEmail("admin@universty");
			// 	form.setRole("ADMIN");
			// 	form.setPhone("091111");
			// 	form.setUsername("admin");
			// 	form.setPassword("1234");
			// 	User myUser=form.toUsering(passwordEncoder);
			// 	Role userRole=repo.findByName("ADMIN");
			// 	myUser.addRole(userRole);
		
			// 	userRepo.save(myUser);
	
			// }
		};
	}
			

			
	// 	};
	// }
	

}
