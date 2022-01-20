package com.alen.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PharmacyController {
    public final PharmacyRepository pharmarepo;

    public final MedicineRepository medrepo;

    @GetMapping("pharmacy/{email}")
    public String pharmacyPage(@PathVariable String email, Model model) {
        if (this.pharmarepo.findByEmail(email) != null) {
            model.addAttribute("pharm", this.pharmarepo.findByEmail(email));
            List<Medicine> medicines = new ArrayList<>();
            this.medrepo.findAllByPharmacy(this.pharmarepo.findByEmail(email)).forEach(i -> medicines.add(i));
            model.addAttribute("medicines", medicines);
        }
        return "pharmacy";
    }

    @GetMapping("pharmacy/{email}/update/")
    public String uploadPage(@PathVariable String email, Model model) {
        if (this.pharmarepo.findByEmail(email) != null) {
            model.addAttribute("pharmacy", this.pharmarepo.findByEmail(email));
        }
        return "uploadMedicine";
    }

    // @PostMapping("pharmacy/{email}/update/")
    // public String processUpload(@PathVariable String email, Model model) {

    // return "uploadMedicine";
    // }

    // @GetMapping("/update")

}
