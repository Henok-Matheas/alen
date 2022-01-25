package com.alen.demo;

import javax.persistence.CascadeType;

// import javax.persistence.CascadeType;
// import javax.persistence.Embedded;.

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
// import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
// import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.alen.demo.security.User;

// import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// import lombok.Builder.Default;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    private String phone;

    // @OneToOne
    // private Address address;

    @OneToOne()
    private User user;

    // @OneToMany(cascade = CascadeType.REMOVE)
    // private List<Medicine> medicines;

    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "")
    // // @Value("${some.key(1,2,3)}")
    // private List<Medicine> medicines;

    // public void addMedicine(Medicine medicine) {
    // this.medicines.add(medicine);
    // }

}
