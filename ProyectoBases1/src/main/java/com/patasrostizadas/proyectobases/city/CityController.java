package com.patasrostizadas.proyectobases.city;

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
public class CityController {
    @Autowired
    private CityService service;

    @GetMapping("/cities")
    public String showCityList(Model model, @Param("keyword") String keyword) {
        List<City> listCities = service.listAll(keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("listCities", listCities);
        return "cities";
    }

    @GetMapping("/cities/new")
    public String showNewForm(Model model) {
        model.addAttribute("city", new City());
        model.addAttribute("pageTitle", "Add New City");
        return "cities_form";
    }

    @PostMapping("/cities/save")
    public String saveCity(City city, RedirectAttributes ra) {
        try {
            service.saveCity(city);
            ra.addFlashAttribute("message", "The city has been saved successfully");
        } catch (CityAlreadyExistsException e) {
            ra.addFlashAttribute("error", "City already exists");
        }
        return "redirect:/cities";
    }

    @PostMapping("/cities/save-existing")
    public String saveExistingCity(City city, RedirectAttributes ra) {
        try {
            service.saveExisting(city);
            ra.addFlashAttribute("message", "The city has been saved successfully");
        } catch (CityNotFoundException e) {
            ra.addFlashAttribute("error", "City doesn't exists");
        }
        return "redirect:/cities";
    }


    @GetMapping("/cities/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            City city = service.getId(id);
            model.addAttribute("city", city);
            model.addAttribute("pageTitle", "Edit City: " + id);
            return "cities_form_edit";
        } catch (CityNotFoundException e) {
            ra.addFlashAttribute("message", "City not found");
            e.printStackTrace();
            return "redirect:/cities";
        }
    }

    @GetMapping("/cities/delete/{id}")
    public String deleteCity(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "City deleted succesfully");
        } catch (CityNotFoundException e) {
            ra.addFlashAttribute("error", "City not found");
        }
        return "redirect:/cities";
    }

}
