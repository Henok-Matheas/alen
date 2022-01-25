package com.alen.demo;

import javax.validation.Valid;

import com.alen.demo.security.User;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("pharmacy/medicine/create")
    public String medicine(@AuthenticationPrincipal User user, Model model) {
        Pharmacy pharmacy = this.pharmarepo.findPharmacyByUser(user);
        if (pharmacy == null) {
            return "error";
        }
        Medicine medicine = new Medicine();
        model.addAttribute("medicine", medicine);
        return "uploadMedicine";
    }

    @PostMapping("pharmacy/medicine/create")
    public String createMedicine(
            @ModelAttribute("medicine") @Valid Medicine medicine, @AuthenticationPrincipal User user,
            RedirectAttributes redirectAttributes, Errors errors) {
        Pharmacy pharmacy = this.pharmarepo.findPharmacyByUser(user);
        if (pharmacy == null) {
            return "error";
        } else if (errors.hasErrors()) {
            return "pageNotFound";
        }
        medicine.setPharmacy(pharmacy);
        this.medrepo.save(medicine);
        redirectAttributes.addAttribute("id", pharmacy.getId());
        return "redirect:/pharmacy/{id}";
    }

    @GetMapping("medicine/{id}")
    public String medicineUpdatePage(@PathVariable("id") Integer id, @AuthenticationPrincipal User user, Model model) {
        Medicine medicine = this.medrepo.findMedicineById(id);
        if (medicine == null) {
            return "error";
        } else if (medicine.getPharmacy().getUser() == user) {
            model.addAttribute("delete", "medicine/delete");
            model.addAttribute("edit", "medicine/update");
        }
        // Medicine medicine;
        model.addAttribute("medicine", medicine);
        return "medicine";
    }

    @PostMapping("medicine/save")
    public String medicineUpdate(
            @ModelAttribute("medicine") @Valid Medicine medicine, RedirectAttributes redirectAttributes,
            Errors errors) {
        Integer id = medicine.getId();
        if (errors.hasErrors()) {
            return "updateMedicine";
        }
        this.medrepo.save(medicine);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/medicine/{id}";
    }

    @GetMapping("/medicine/delete/{id}")
    public String deleteMedicine(@PathVariable("id") Integer id, @AuthenticationPrincipal User user,
            RedirectAttributes redirectAttributes, Model model) {
        Medicine medicine = this.medrepo.findMedicineById(id);
        if (user == null || medicine == null) {
            return "error";
        }
        Pharmacy pharmacy = medicine.getPharmacy();
        Integer p_id = pharmacy.getId();
        if (this.pharmarepo.findPharmacyByUser(user) != pharmacy) {
            redirectAttributes.addAttribute("id", this.pharmarepo.findPharmacyByUser(user).getId());
            return "redirect:/pharmacy/{id}";
        }
        redirectAttributes.addAttribute("id", p_id);
        this.medrepo.deleteById(id);
        return "redirect:/pharmacy/{id}";
    }

}
