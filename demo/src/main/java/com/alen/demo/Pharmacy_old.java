package com.alen.demo;

// import javax.persistence.CascadeType;
// import javax.persistence.Embedded;

// import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Pharmacy_old {
    @Id
    private Long id;

    @NotBlank
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    private String password;

    private String phone;

    // @Embedded
    // private Address address;

    @OneToOne
    private User host;

    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "")
    // // @Value("${some.key(1,2,3)}")
    // private List<Medicine> medicines;

    // public void addMedicine(Medicine medicine) {
    // this.medicines.add(medicine);
    // }

}
