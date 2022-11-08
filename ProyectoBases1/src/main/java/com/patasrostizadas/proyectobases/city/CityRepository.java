package com.patasrostizadas.proyectobases.city;

import com.patasrostizadas.proyectobases.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {

    Long countById(Integer id);

    @Query("SELECT c FROM City c WHERE " + "CONCAT(c.name, c.countryCode,c.district, c.id, c.population) " +
            "LIKE %?1%")
    List<City> findAll(String keyword);



}
