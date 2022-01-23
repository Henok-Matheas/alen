package com.alen.demo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.alen.demo.security.User;
import com.alen.demo.security.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PharmacyController {
    public final PharmacyRepository pharmarepo;

    public final MedicineRepository medrepo;

    public final UserRepository userrepo;

    @GetMapping("user/{id}/pharmacy/create")
    public String createPharmacy(Model model) {
        model.addAttribute("pharmacy", new Pharmacy());
        return "pharmacyCreate";
    }

    @PostMapping("user/{id}/pharmacy/create")
    public String processPharmacy(@Valid Pharmacy pharmacy, @PathVariable Integer id, Errors errors) {
        User user = this.userrepo.findUserById(id);
        if (errors.hasErrors()) {
            return "pharmacyCreate";
        } else if (user == null) {
            return "home";
        }

        pharmacy.setUser(user);
        this.pharmarepo.save(pharmacy);
        return "pharmacy";
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

    @GetMapping("pharmacy/{id}/update")
    public String uploadPage(@PathVariable Integer id, Model model) {
        Pharmacy pharmacy = this.pharmarepo.findPharmacyById(id);
        if (pharmacy == null) {
            return "error";
        }
        model.addAttribute("pharmacy", pharmacy);
        return "updatePharmacy";
    }

    @PostMapping("pharmacy/save")
    public String pharmacyUpdate(@Valid Pharmacy pharmacy, Errors errors) {
        Integer id = pharmacy.getId();
        String path = "{" + Integer.toString(id) + "}";
        if (errors.hasErrors()) {
            return "updatePharmacy";
        }
        this.pharmarepo.save(pharmacy);
        return "redirect:/pharmacy/" + path;
    }

    @GetMapping("/pharmacy/delete/{id}")
    public String deletePharmacy(@PathVariable("id") Integer id, Model model) {
        pharmarepo.deleteById(id);
        return "redirect:/user/{id}";
    }

    @GetMapping("/medicine/delete/{id}")
    public String deleteMedicine(@PathVariable("id") Integer id, Model model) {
        medrepo.deleteById(id);
        return "redirect:/pharmacy/{id}";
    }

}
