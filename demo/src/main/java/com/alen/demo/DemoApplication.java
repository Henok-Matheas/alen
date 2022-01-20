package com.alen.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication {
	public final PharmacyRepository pharmarepo;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner dataLoad(PharmacyRepository repo) {
	// return args -> {
	// repo.save(new Pharmacy("FLTO", "Flour Tortilla", "++++++232323"));
	// };
	// }

	// @Bean
	// public CommandLineRunner dataLoader(MedicineRepository repo) {
	// return args -> {
	// repo.save(new Medicine(2, "Flour Tortilla", 20.0, 2,
	// this.pharmarepo.findByEmail("FLTO")));
	// repo.save(new Medicine(3, "hello", 22.0, 1,
	// this.pharmarepo.findByEmail("FLTO")));
	// };
	// }
}
