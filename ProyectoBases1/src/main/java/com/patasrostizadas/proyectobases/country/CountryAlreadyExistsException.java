package com.patasrostizadas.proyectobases.country;

public class CountryAlreadyExistsException extends Throwable {
    public CountryAlreadyExistsException(String message) {
        super(message);
    }
}
