package com.patasrostizadas.proyectobases.languages;

import javax.persistence.*;

@Entity
@Table(name = "CountryLanguage")
public class CountryLanguage {
    @Column(name = "CountryCode")
    private String countryCode;

    @Id
    @Column(name = "Language")
    private String language;

    @Column(name = "IsOfficial")
    private boolean isOfficial;

    @Column(name = "Percentage")
    private Integer percentage;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isOfficial() {
        return isOfficial;
    }

    public void setOfficial(boolean official) {
        isOfficial = official;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

}


