package com.alen.demo;

import com.alen.demo.security.Pharmacy;
import com.alen.demo.security.PharmacyRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller

public class updatePharmacyAdminController {
    PharmacyRepository pharmacyRepo;
    public updatePharmacyAdminController(PharmacyRepository pharmacyRepo){
        this.pharmacyRepo=pharmacyRepo;
    }
    @GetMapping("/updatePharmacyAdmin/{id}")
    public String updateUser(Model model, @PathVariable Integer id){
        Pharmacy myUser=pharmacyRepo.findById(id);
        model.addAttribute("pharmacy",myUser);

        return "updatePharmacyAdmin";
    }
    @PostMapping("/updatePharmacyAdmin/{id}")
    public String deletPharmacy(@PathVariable Integer id){
        pharmacyRepo.deleteById(id);
        return "redirect:/listOfPharmacy";
    }

    
}
