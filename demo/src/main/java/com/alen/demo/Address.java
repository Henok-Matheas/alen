package com.alen.demo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
// import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 2, message = "City must be at least 2 characters long")
    private String city;

    @NotBlank
    @Size(min = 2, message = "subCity must be at least 2 characters long")
    private String subCity;

    @NotBlank
    @Size(min = 2, message = "kebele must be at least 2 characters long")
    private String kebele;

    @NotNull
    @Min(value = 1, message = "House number must be greater than or equal to one")
    private int houseNo;

    @OneToOne()
    private Pharmacy pharmacy;

}
