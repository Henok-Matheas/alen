package com.alen.demo;

import com.alen.demo.security.PharmacyRepository;

import com.alen.demo.security.Pharmacy;
import java.util.List;
import java.util.ArrayList;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/listOfPharmacyAdmin")
@RequiredArgsConstructor
public class listOfPharmacyAdminController {
    private final PharmacyRepository pharmacyRepo;
    @GetMapping
    public String viewPharmacy(Model model){
        List<Pharmacy> pharmacy=new ArrayList<>();
        this.pharmacyRepo.findAll().forEach(i->pharmacy.add(i));
        model.addAttribute("pharmacy", pharmacy);
        return "listOfPharmacy";
    }
    
}
