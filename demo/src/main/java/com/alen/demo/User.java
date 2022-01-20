package com.alen.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
// @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class User {
    @Id
    private String email;

    @NotBlank
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    private String phone;

    @OneToOne
    private Address address;

}
