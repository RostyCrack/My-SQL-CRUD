package com.patasrostizadas.proyectobases.languages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired private LanguageRepository repo;

    public List<CountryLanguage> listAll(String keyword){
        if(keyword != null){
            return repo.findAll(keyword);
        }
        return repo.findAll();
    }

    public void saveLanguage(CountryLanguage language) throws LanguageAlreadyExistsException {
        String name = language.getLanguage();
        Optional<CountryLanguage> exists = repo.findById(name);
        if(exists.isPresent()){

            throw new LanguageAlreadyExistsException("Language with name " + name + " already exists" );
        }
        else{
            repo.save(language);
        }
    }

    public CountryLanguage getLanguage(String language) throws LanguageNotFoundException {
        Optional<CountryLanguage> result = repo.findById(language);
        if (result.isPresent()){
            return result.get();
        }
        throw new LanguageNotFoundException("Could not find language with name "+ language);
    }

    public void saveExisting(CountryLanguage language) throws LanguageNotFoundException {
        String id = language.getLanguage();
        Optional<CountryLanguage> exists = repo.findById(id);
        if(exists.isPresent()){
            repo.save(language);
        }
        else{
            throw new LanguageNotFoundException("Language not found");
        }

    }

    public void delete(String id) throws LanguageNotFoundException {
        Long count = repo.countByLanguage(id);
        if (count == null || count == 0){
            throw new LanguageNotFoundException("Could not find language with name: "+ id);
        }
        repo.deleteById(id);
    }
}
