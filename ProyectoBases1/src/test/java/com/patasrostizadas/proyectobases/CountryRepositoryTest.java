package com.patasrostizadas.proyectobases;

import com.patasrostizadas.proyectobases.country.Country;
import com.patasrostizadas.proyectobases.country.CountryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)

public class CountryRepositoryTest {
    @Autowired
    private CountryRepository repo;



    @Test
    public void testAddNew(){
        Country country = new Country();
        country.setCode("JAV");
        country.setName("Java");
        country.setContinent("Asia");
        country.setRegion("jaja");
        country.setSurfaceArea(2344F);
        country.setPopulation(2);
        country.setCode2("df");
        country.setLocalName(":C");
        country.setGovernmentForm("monarquia");
        country.setCode2("dd");

        Country savedCountry = repo.save(country);

        Assertions.assertThat(savedCountry).isNotNull();
    }

    @Test
    public void testListAll(){
        Iterable<Country> countries = repo.findAll();
        Assertions.assertThat(countries).hasSizeGreaterThan(0);

        for (Country country: countries){
            System.out.println(country);
        }
    }

    @Test
    public void testUpdate(){
        String code = "JAV";
        Optional<Country> optionalCountry = repo.findById(code);
        Country country = optionalCountry.get();
        country.setContinent("North");
        repo.save(country);

        Country updatedCountry = repo.findById("JAV").get();
        Assertions.assertThat(updatedCountry.getContinent()).isEqualTo("North America");
    }

    @Test
    public void testGet(){
        String code = "JAV";
        Optional<Country> optionalCountry = repo.findById(code);
        Assertions.assertThat(optionalCountry).isPresent();
        System.out.println(optionalCountry.get());
    }


    @Test
    public void testDelete(){
        String code = "JAV";
        repo.deleteById(code);
        Optional<Country> optionalCountry = repo.findById(code);
        Assertions.assertThat(optionalCountry).isNotPresent();
    }

}
