package com.patasrostizadas.proyectobases.languages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LanguageRepository extends JpaRepository<CountryLanguage, String> {

    Long countByLanguage(String name);

    @Query("SELECT l FROM CountryLanguage l WHERE " + "CONCAT(l.countryCode,l.language, l.isOfficial, l.percentage) " +
            "LIKE %?1%")
    List<CountryLanguage> findAll(String keyword);

}
