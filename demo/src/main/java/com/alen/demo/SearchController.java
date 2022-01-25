package com.alen.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SearchController {
    @Autowired
    private final MedicineRepository medrepo;

    @GetMapping("user/{id}/Search")
    public String searchResult(@PathVariable Long id, Model model, @RequestParam("q") String q) {
        List<Medicine> medicines = (List<Medicine>) medrepo.SearchBykeyword(q);

        List<Pharmacy> pharmacies = new ArrayList<>();

        for (Medicine medicine : medicines) {
            pharmacies.add(medicine.getPharmacy());
        }

        model.addAttribute("medicines", medicines);
        model.addAttribute("pharmacies", pharmacies);
        model.addAttribute("q", q);
        return "search";
    }
}
