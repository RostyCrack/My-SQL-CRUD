package com.patasrostizadas.proyectobases.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired private CountryRepository repo;

    public List<Country> listAll(String keyword){
        if(keyword != null){
            return repo.findAll(keyword);
        }
        return repo.findAll();
    }

    public void save(Country country) throws CountryAlreadyExistsException{
        String code = country.getCode();
        Optional<Country> exists = repo.findById(code);
        if(exists.isPresent()){

            throw new CountryAlreadyExistsException("Country with code " + code + " already exists" );
        }
        else{
            repo.save(country);
        }
    }

    public Country getCode(String code) throws CountryNotFoundException{
        Optional<Country> result = repo.findById(code);
        if (result.isPresent()){
            return result.get();
        }
        throw new CountryNotFoundException("Could not find country with code "+ code);
    }

    public void saveExisting(Country country) throws CountryNotFoundException {
        String code = country.getCode();
        Optional<Country> exists = repo.findById(code);
        if(exists.isPresent()){
            repo.save(country);
        }
        else{
            throw new CountryNotFoundException("Country not found");
        }

    }

    public void delete(String code) throws CountryNotFoundException {
        Long count = repo.countByCode(code);
        if (count == null || count == 0){
            throw new CountryNotFoundException("Could not find country with code: "+ code);
        }
        repo.deleteById(code);
    }
}
