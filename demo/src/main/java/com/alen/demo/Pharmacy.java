package com.alen.demo;

// import java.util.List;

import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.OneToMany;
// import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
// import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Pharmacy {
    @Id
    private String email;

    @NotBlank
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    private String phone;

    // @OneToOne
    // private Address address;

    // @OneToMany
    // private List<Medicine> medicines;

}
