package com.patasrostizadas.proyectobases.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired private CityRepository repo;

    public List<City> listAll(String keyword){
        if(keyword != null){
            return repo.findAll(keyword);
        }
        return repo.findAll();
    }

    public void saveCity(City city) throws CityAlreadyExistsException {
        Integer id = city.getId();
        Optional<City> exists = repo.findById(id);
        if(exists.isPresent()){

            throw new CityAlreadyExistsException("City with id " + id + " already exists" );
        }
        else{
            repo.save(city);
        }
    }

    public City getId(Integer id) throws CityNotFoundException {
        Optional<City> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new CityNotFoundException("Could not find city with id "+ id);
    }

    public void saveExisting(City city) throws CityNotFoundException {
        Integer id = city.getId();
        Optional<City> exists = repo.findById(id);
        if(exists.isPresent()){
            repo.save(city);
        }
        else{
            throw new CityNotFoundException("City not found");
        }

    }

    public void delete(Integer id) throws CityNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0){
            throw new CityNotFoundException("Could not find city with id: "+ id);
        }
        repo.deleteById(id);
    }
}
