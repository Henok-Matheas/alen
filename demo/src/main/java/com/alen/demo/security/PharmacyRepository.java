package com.alen.demo.security;

import org.springframework.data.repository.CrudRepository;

public interface PharmacyRepository extends CrudRepository<Pharmacy, String> {
    Pharmacy name(String name);
    Pharmacy findById(Integer id);
    Pharmacy deleteById( Integer id);

}
