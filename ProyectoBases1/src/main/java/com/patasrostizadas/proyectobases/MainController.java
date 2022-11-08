package com.patasrostizadas.proyectobases;

import com.patasrostizadas.proyectobases.country.CountryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final CountryRepository countryRepository;

    public MainController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping("")
    public String showHomePage(){
        System.out.println("Main controller v");
        return "index";
    }



}
