package com.patasrostizadas.proyectobases.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface CountryRepository extends JpaRepository<Country, String> {
    Long countByCode(String code);

    @Query("SELECT c FROM Country c WHERE " + "CONCAT(c.name, c.code,c.continent,c.region,c.localName,c.governmentForm, c.headOfState, c.code2, c.population, c.capital, c.lifeExpectancy) " +
            "LIKE %?1%")
    List<Country> findAll(String keyword);



}
