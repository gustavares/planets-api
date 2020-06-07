package com.starwars.api.model;

import java.util.List;

import com.starwars.api.model.SwapiPlanet;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiResult {
    
    private List<SwapiPlanet> results;

    public SwapiResult() {

    }

    public SwapiResult(List<SwapiPlanet> results) {
        this.results = results;
    }

    public List<SwapiPlanet> getResults() {
        return this.results;
    }

    public void setResults(List<SwapiPlanet> results) {
        this.results = results;
    }
}