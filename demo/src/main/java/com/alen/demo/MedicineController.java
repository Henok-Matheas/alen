package com.alen.demo;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MedicineController {
    public final PharmacyRepository pharmarepo;

    public final MedicineRepository medrepo;

    @GetMapping("pharmacy/{id}/medicine/create")
    public String medicine(@PathVariable Integer id, Model model) {
        Pharmacy pharmacy = this.pharmarepo.findPharmacyById(id);
        if (pharmacy == null) {
            return "error";
        }
        Medicine medicine = new Medicine();
        medicine.setPharmacy(pharmacy);
        model.addAttribute("medicine", medicine);
        return "uploadMedicine";
    }

    @PostMapping("pharmacy/{id}/medicine/create")
    public String createMedicine(@Valid Medicine medicine, @PathVariable Integer id, Errors errors) {
        Pharmacy pharmacy = this.pharmarepo.findPharmacyById(id);
        if (pharmacy == null) {
            return "error";
        } else if (errors.hasErrors()) {
            return "medicineForm";
        }
        medicine.setPharmacy(pharmacy);
        this.medrepo.save(medicine);
        return "redirect:/pharmacy/{id}";
    }

    @GetMapping("pharmacy/{p_id}/medicine/{id}")
    public String pharmacyPage(@PathVariable("p_id") Integer p_id, @PathVariable("id") Integer id, Model model) {
        Pharmacy pharmacy = this.pharmarepo.findPharmacyById(p_id);
        Medicine medicine = this.medrepo.findMedicineById(id);
        if (pharmacy == null) {
            return "redirect:/error";
        }
        if (medicine == null) {
            return "redirect:/error";
        }
        // Medicine medicine;
        model.addAttribute("medicine", medicine);
        model.addAttribute("pharmacy", pharmacy);
        return "medicine";
    }

    @GetMapping("/medicine/delete/{id}")
    public String deleteMedicine(@PathVariable("id") Integer id, Model model) {
        medrepo.deleteById(id);
        return "redirect:/pharmacy/{id}";
    }

}
