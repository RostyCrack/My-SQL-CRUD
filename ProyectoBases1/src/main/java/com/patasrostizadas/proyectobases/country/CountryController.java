package com.patasrostizadas.proyectobases.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CountryController {

    @Autowired
    private CountryService service;

    @GetMapping("/countries")
    public String showCountryList(Model model, @Param("keyword") String keyword) {
        List<Country> listCountries = service.listAll(keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("listCountries", listCountries);
        return "countries";
    }

    @GetMapping("/countries/new")
    public String showNewForm(Model model) {
        model.addAttribute("country", new Country());
        model.addAttribute("pageTitle", "Add New Country");
        return "country_form";
    }

    @PostMapping("/country/save")
    public String saveCountry(Country country, RedirectAttributes ra) {
        try {
            service.save(country);
            ra.addFlashAttribute("message", "The country has been saved successfully");
        } catch (CountryAlreadyExistsException e) {
            ra.addFlashAttribute("error", "Country already exists");
        }
        return "redirect:/countries";
    }

    @PostMapping("/country/save-existing")
    public String saveExistingCountry(Country country, RedirectAttributes ra) {
        try {
            service.saveExisting(country);
            ra.addFlashAttribute("message", "The country has been saved successfully");
        } catch (CountryNotFoundException e) {
            ra.addFlashAttribute("error", "Country doesn't exists");
        }
        return "redirect:/countries";
    }


    @GetMapping("/countries/edit/{code}")
    public String showEditForm(@PathVariable("code") String code, Model model, RedirectAttributes ra) {
        try {
            Country country = service.getCode(code);
            model.addAttribute("country", country);
            model.addAttribute("pageTitle", "Edit Country: " + code);
            return "country_form_edit";
        } catch (CountryNotFoundException e) {
            ra.addFlashAttribute("message", "Country not found");
            e.printStackTrace();
            return "redirect:/countries";
        }
    }

    @GetMapping("/countries/delete/{code}")
    public String deleteCountry(@PathVariable("code") String code, RedirectAttributes ra) {
        try {
            service.delete(code);
            ra.addFlashAttribute("message", "Country deleted succesfully");
        } catch (CountryNotFoundException e) {
            ra.addFlashAttribute("error", "Country not found");
        }
        return "redirect:/countries";
    }
}