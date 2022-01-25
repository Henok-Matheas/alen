package com.alen.demo;

import com.alen.demo.security.User;

import org.springframework.data.repository.CrudRepository;

public interface PharmacyRepository extends CrudRepository<Pharmacy, Integer> {
    Pharmacy findPharmacyById(Integer id);

    Pharmacy findPharmacyByUser(User user);
}
