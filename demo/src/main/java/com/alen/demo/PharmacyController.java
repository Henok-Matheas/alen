package com.alen.demo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.alen.demo.security.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PharmacyController {
    @Autowired
    private final PharmacyRepository pharmarepo;

    @Autowired
    private final MedicineRepository medrepo;

    @Autowired
    private final AddressRepository addressrepo;

    @GetMapping("user/pharmacy/create")
    public String createPharmacy(Model model, @AuthenticationPrincipal User user) {
        Pharmacy pharmacy = new Pharmacy();
        Address address = new Address();
        model.addAttribute("pharmacy", pharmacy);
        model.addAttribute("address", address);
        return "pharmacyCreate";
    }

    @PostMapping("user/pharmacy/create")
    public String processPharmacy(
            @ModelAttribute("pharmacy") @Valid Pharmacy pharmacy, Errors errors,
            @ModelAttribute("address") @Valid Address address, Errors erorrs,
            @AuthenticationPrincipal User user) {

        if (errors.hasErrors() || erorrs.hasErrors()) {
            return "pharmacyCreate";
        } else if (user == null) {
            return "redirect:/login";
        } else if (this.pharmarepo.findPharmacyByUser(user) != null) {
            return "pharmacy";
        }

        pharmacy.setUser(user);
        address.setPharmacy(pharmacy);
        this.pharmarepo.save(pharmacy);
        this.addressrepo.save(address);

        return "redirect:/user/pharmacy";
    }

    @GetMapping("pharmacy/{id}")
    public String pharmacyPage(@PathVariable Integer id, Model model) {
        Pharmacy pharmacy = this.pharmarepo.findPharmacyById(id);
        if (pharmacy == null) {
            return "error";
        }
        model.addAttribute("pharmacy", pharmacy);
        List<Medicine> medicines = new ArrayList<>();
        this.medrepo.findMedicineByPharmacy(pharmacy).forEach(i -> medicines.add(i));
        model.addAttribute("medicines", medicines);
        return "pharmacy";
    }

    @GetMapping("user/pharmacy")
    public String pharmacyPage(@AuthenticationPrincipal User user, Model model) {
        Pharmacy pharmacy = this.pharmarepo.findPharmacyByUser(user);
        if (pharmacy == null) {
            return "error";
        }
        model.addAttribute("pharmacy", pharmacy);
        Address address = this.addressrepo.findAddressByPharmacy(pharmacy);
        model.addAttribute("address", address);
        List<Medicine> medicines = new ArrayList<>();
        this.medrepo.findMedicineByPharmacy(pharmacy).forEach(i -> medicines.add(i));
        model.addAttribute("medicines", medicines);
        return "pharmacy";
    }

    @GetMapping("user/pharmacy/update")
    public String updatePharmacyPage(@AuthenticationPrincipal User user, Model model) {
        Pharmacy pharmacy = this.pharmarepo.findPharmacyByUser(user);
        if (pharmacy == null) {
            return "error";
        }
        Address address = this.addressrepo.findAddressByPharmacy(pharmacy);
        model.addAttribute("address", address);
        model.addAttribute("pharmacy", pharmacy);
        return "updatePharmacy";
    }

    @PostMapping("user/pharmacy/update")
    public String pharmacyUpdate(@ModelAttribute("pharmacy") @Valid Pharmacy pharmacy, Errors errors,
            @ModelAttribute("address") @Valid Address address,
            Errors erorrs, @AuthenticationPrincipal User user) {

        if (user == null) {
            return "error";
        }
        if (errors.hasErrors() || erorrs.hasErrors()) {
            return "updatePharmacy";
        }
        pharmacy.setUser(user);
        address.setPharmacy(pharmacy);
        this.pharmarepo.save(pharmacy);
        this.addressrepo.save(address);
        return "redirect:/user/pharmacy";
    }

    @GetMapping("user/pharmacy/delete")
    public String deletePharmacy(Model model, @AuthenticationPrincipal User user) {
        Pharmacy pharmacy = this.pharmarepo.findPharmacyByUser(user);
        if (pharmacy == null) {
            return "redirect:/";
        }
        Address address = this.addressrepo.findAddressByPharmacy(pharmacy);
        this.medrepo.findMedicineByPharmacy(pharmacy).forEach(i -> this.medrepo.deleteById(i.getId()));
        this.addressrepo.delete(address);
        this.pharmarepo.delete(pharmacy);
        return "redirect:/user";
    }
}
