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

    @GetMapping("/updatePharmacyAdmin/{id}")
    public String updateUser(Model model, @PathVariable Integer id) {
        Pharmacy pharmacy = pharmarepo.findPharmacyById(id);
        model.addAttribute("pharmacy", pharmacy);
        return "updatePharmacyAdmin";
    }

    @PostMapping("/updatePharmacyAdmin/{id}")
    public String deletPharmacy(@Valid @ModelAttribute("pharmacy") Pharmacy pharmacy, Errors errors,
            @PathVariable Integer id) {
        if (errors.hasErrors()) {
            return "updatePharmacyAdmin";
        }
        this.medrepo.findMedicineByPharmacy(pharmacy).forEach(i -> this.medrepo.deleteById(i.getId()));
        pharmarepo.deleteById(id);
        return "redirect:/listOfPharmacy";
    }

    // public updateUserAdminController(PasswordEncoder
    // passwordEncoder,UserRepository userrepo,RoleRepository roleRepo){
    // this.passwordEncoder=passwordEncoder;
    // this.userrepo=userRepo;
    // this.roleRepo=roleRepo;
    // }

    // @PostMapping("/updateUserAdmin/{id}")
    // public String changeUser(@PathVariable Integer id, RegistrationForm form) {
    // User myUserRepo = this.userrepo.findUserById(id);
    // form.setPassword(myUserRepo.getPassword());
    // User myUser = form.toUsering();
    // myUser.setId(id);
    // userRepo.delete(userRepo.findById(id));
    // Role userRole = roleRepo.findByName("USER");
    // myUser.addRole(userRole);
    // userRepo.save(myUser);

    // return "redirect:/listOfUser";
    // }

    // @PostMapping("/deleteUserAdmin/{id}")
    // public String deleteUser(@PathVariable Integer id) {
    // User myUserRepo = userRepo.findById(id);
    // // userRepo.deleteById(id);
    // return "listOfUser";
    // }

}
