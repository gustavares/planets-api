package com.starwars.api.service;

import java.util.List;

import com.starwars.api.model.Planet;

public interface PlanetService {
    
    List<Planet> findAll();
    List<Planet> findByName(String name);
    Planet findById(String id);
    Planet savePlanet(Planet planet);
}