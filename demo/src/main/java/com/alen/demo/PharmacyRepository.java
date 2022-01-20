package com.alen.demo;

// import java.util.Optional;

// import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PharmacyRepository extends CrudRepository<Pharmacy, String> {
    Pharmacy findByname(String name);

    Pharmacy findByEmail(String email);

    // Pharmacy findByid(Long id);
}
