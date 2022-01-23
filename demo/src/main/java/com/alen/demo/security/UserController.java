package com.alen.demo.security;

import com.alen.demo.MedicineRepository;
import com.alen.demo.Pharmacy;
import com.alen.demo.PharmacyRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    public final UserRepository userrepo;

    public final PharmacyRepository pharmrepo;

    public final MedicineRepository medrepo;

    @GetMapping("user/{id}")
    public String userpage(@PathVariable Integer id, Model model) {
        User user = this.userrepo.findUserById(id);
        if (user == null) {
            log.info("user is null");
            return "home";
        }
        log.info("user has a value");
        model.addAttribute("user", user);
        Pharmacy pharmacy = this.pharmrepo.findPharmacyByUser(user);
        model.addAttribute("pharmacy", pharmacy);
        return "user";
    }

}