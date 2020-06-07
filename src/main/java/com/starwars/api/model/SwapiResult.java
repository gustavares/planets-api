package com.starwars.api.model;

import java.util.List;

import com.starwars.api.model.SwapiPlanet;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiResult {
    
    private List<SwapiPlanet> planetList;

    public SwapiResult() {

    }

    public SwapiResult(List<SwapiPlanet> results) {
        this.planetList = results;
    }

    public List<SwapiPlanet> getPlanetList() {
        return this.planetList;
    }

    public void setResults(List<SwapiPlanet> planets) {
        this.planetList = planets;
    }
}