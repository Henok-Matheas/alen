package com.alen.demo.security;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);
    User findById(Integer id);
    //User findByRole(String role);

}
