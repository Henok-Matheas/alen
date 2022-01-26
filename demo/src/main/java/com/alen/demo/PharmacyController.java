package com.alen.demo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.alen.demo.security.User;
import com.alen.demo.security.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    private final UserRepository userrepo;

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
            @ModelAttribute("pharmacy") @Valid Pharmacy pharmacy,
            @ModelAttribute("address") @Valid Address address, Errors errors,
            @AuthenticationPrincipal User user) {

        if (errors.hasErrors()) {
            return "pharmacyCreate";
        } else if (user == null) {
            return "redirect:/login";
        } else if (this.pharmarepo.findPharmacyByUser(user) != null) {
            return "pharmacy";
        }

        pharmacy.setAddress(address);
        this.addressrepo.save(address);
        pharmacy.setUser(user);
        this.pharmarepo.save(pharmacy);
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
        List<Medicine> medicines = new ArrayList<>();
        this.medrepo.findMedicineByPharmacy(pharmacy).forEach(i -> medicines.add(i));
        model.addAttribute("medicines", medicines);
        return "pharmacy";
    }

    @GetMapping("pharmacy/update/{id}")
    public String uploadPage(@PathVariable Integer id, Model model) {
        Pharmacy pharmacy = this.pharmarepo.findPharmacyById(id);
        if (pharmacy == null) {
            return "error";
        }
        model.addAttribute("pharmacy", pharmacy);
        return "updatePharmacy";
    }

    @PostMapping("pharmacy/save")
    public String pharmacyUpdate(
            @ModelAttribute("pharmacy") @Valid Pharmacy pharmacy,
            Errors errors, RedirectAttributes redirectAttributes) {
        Integer id = pharmacy.getId();
        if (errors.hasErrors()) {
            return "updatePharmacy";
        }
        this.pharmarepo.save(pharmacy);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/pharmacy/{id}";
    }

    @GetMapping("/pharmacy/delete/{id}")
    public String deletePharmacy(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Pharmacy pharmacy = this.pharmarepo.findPharmacyById(id);
        if (pharmacy == null) {
            return "redirect:/";
        }
        Address address = pharmacy.getAddress();
        this.addressrepo.delete(address);
        this.medrepo.findMedicineByPharmacy(pharmacy).forEach(i -> this.medrepo.deleteById(i.getId()));
        this.pharmarepo.delete(pharmacy);
        return "redirect:/user/";
    }
}
