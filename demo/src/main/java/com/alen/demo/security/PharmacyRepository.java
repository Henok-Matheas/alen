package com.alen.demo.security;

import org.springframework.data.repository.CrudRepository;

public interface PharmacyRepository extends CrudRepository<Pharmacy, String> {
    Pharmacy findByUsername(String username);

}
