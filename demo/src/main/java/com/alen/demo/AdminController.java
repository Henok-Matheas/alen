package com.alen.demo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.alen.demo.security.RegistrationForm;
import com.alen.demo.security.User;
import com.alen.demo.security.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Controller
@RequiredArgsConstructor
public class AdminController {
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepo;
    @Autowired
    private final PharmacyRepository pharmarepo;

    @Autowired
    private final MedicineRepository medrepo;

    @Autowired
    private final UserRepository userrepo;

    @Autowired
    private final AddressRepository addressrepo;

    @GetMapping("/admin")
    public String viewAdmin() {
        return "admin";
    }

    @GetMapping("/listOfPharmacyAdmin")
    public String viewPharmacy(Model model) {
        List<Pharmacy> pharmacies = new ArrayList<>();
        this.pharmarepo.findAll().forEach(i -> pharmacies.add(i));
        model.addAttribute("pharmacies", pharmacies);
        return "listOfPharmacy";
    }

    @GetMapping("/listOfUserAdmin")
    public String retrunUserList(Model model) {
        List<User> users = new ArrayList<>();
        this.userrepo.findAll().forEach(i -> users.add(i));
        model.addAttribute("users", users);

        return "listOfUser";
    }

    @GetMapping("/deletePharmacyAdmin/{id}")
    public String deletePharmacy(@PathVariable Integer id) {
        Pharmacy pharmacy = this.pharmarepo.findPharmacyById(id);
        Address address = this.addressrepo.findAddressByPharmacy(pharmacy);
        this.medrepo.findMedicineByPharmacy(pharmacy).forEach(i -> this.medrepo.deleteById(i.getId()));
        this.addressrepo.delete(address);
        this.pharmarepo.delete(pharmacy);
        return "redirect:/listOfPharmacyAdmin";
    }

}
