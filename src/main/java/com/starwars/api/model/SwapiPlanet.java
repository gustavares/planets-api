package com.starwars.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiPlanet {
    
    private List<String> films;

    public SwapiPlanet(){

    };
    
    public SwapiPlanet(List<String> films) {
        this.films = films;
    }

    public List<String> getFilms() {
        return this.films;
    }
}
