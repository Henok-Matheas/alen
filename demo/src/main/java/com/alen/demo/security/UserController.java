package com.alen.demo.security;

import javax.validation.Valid;

import com.alen.demo.Address;
import com.alen.demo.AddressRepository;
import com.alen.demo.MedicineRepository;
import com.alen.demo.Pharmacy;
import com.alen.demo.PharmacyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final PharmacyRepository pharmrepo;

    @Autowired
    private final MedicineRepository medrepo;

    @Autowired
    private final UserRepository userrepo;

    @Autowired
    private final AddressRepository addressrepo;

    @GetMapping("/user")
    public String userpage(@AuthenticationPrincipal User user, Model model) {
        if (user == null) {
            return "home";
        }
        model.addAttribute("user", user);
        Pharmacy pharmacy = this.pharmrepo.findPharmacyByUser(user);
        model.addAttribute("pharmacy", pharmacy);
        return "user";
    }

    @GetMapping("user/edit")
    public String updateUser(@AuthenticationPrincipal User user, Model model) {
        if (user == null) {
            return "home";
        }
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("user/edit")
    public String editUser(@ModelAttribute("user") @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "editUser";
        }
        this.userrepo.save(user);
        return "editUser";
    }

    @GetMapping("user/delete")
    public String deleteUser(@AuthenticationPrincipal User user, Model model) {
        if (user == null) {
            return "home";
        }
        Pharmacy pharmacy = this.pharmrepo.findPharmacyByUser(user);
        Address address = pharmacy.getAddress();
        this.addressrepo.delete(address);
        this.medrepo.findMedicineByPharmacy(pharmacy).forEach(i -> this.medrepo.deleteById(i.getId()));
        this.pharmrepo.delete(pharmacy);
        this.userrepo.delete(user);
        return "home";
    }

}