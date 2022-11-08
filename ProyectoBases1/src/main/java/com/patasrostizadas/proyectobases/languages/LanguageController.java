package com.patasrostizadas.proyectobases.languages;

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
public class LanguageController {

    @Autowired private LanguageService service;

    @GetMapping("/languages")
    public String showLanguageList(Model model, @Param("keyword") String keyword) {
        List<CountryLanguage> listCountryLanguages = service.listAll(keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("listCountryLanguages", listCountryLanguages);
        return "languages";
    }

    @GetMapping("/languages/new")
    public String showNewForm(Model model) {
        model.addAttribute("language", new CountryLanguage());
        model.addAttribute("pageTitle", "Add New Language");
        return "language_form";
    }

    @PostMapping("/languages/save")
    public String saveCity(CountryLanguage language, RedirectAttributes ra) {
        try {
            service.saveLanguage(language);
            ra.addFlashAttribute("message", "The language has been saved successfully");
        } catch (LanguageAlreadyExistsException e) {
            ra.addFlashAttribute("error", "Language already exists");
        }
        return "redirect:/languages";
    }

    @PostMapping("/languages/save-existing")
    public String saveExistingCity(CountryLanguage language, RedirectAttributes ra) {
        try {
            service.saveExisting(language);
            ra.addFlashAttribute("message", "The language has been saved successfully");
        } catch (LanguageNotFoundException e) {
            ra.addFlashAttribute("error", "Language doesn't exists");
        }
        return "redirect:/languages";
    }


    @GetMapping("/languages/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model, RedirectAttributes ra) {
        try {
            CountryLanguage language = service.getLanguage(id);
            model.addAttribute("language", language);
            model.addAttribute("pageTitle", "Edit language: " + id);
            return "language_form_edit";
        } catch (LanguageNotFoundException e) {
            ra.addFlashAttribute("message", "Language not found");
            e.printStackTrace();
            return "redirect:/languages";
        }
    }

    @GetMapping("/languages/delete/{id}")
    public String deleteCity(@PathVariable("id") String id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "Language deleted succesfully");
        } catch (LanguageNotFoundException e) {
            ra.addFlashAttribute("error", "Language not found");
        }
        return "redirect:/languages";
    }
}
