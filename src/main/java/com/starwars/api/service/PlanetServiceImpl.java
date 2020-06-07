package com.starwars.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starwars.api.model.Planet;
import com.starwars.api.model.SwapiPlanet;
import com.starwars.api.repository.PlanetRepository;
import com.starwars.swapiClient.SwapiPlanetService;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository planetRepository;
    
    @Autowired SwapiPlanetService swapiService;

    @Override
    public List<Planet> findAll() {
        return planetRepository.findAll();
    }

    @Override
    public List<Planet> findByName(String name) {
        return planetRepository.findByName(name);
    }

    @Override
    public Planet findById(String id) {
        return planetRepository.findById(id).orElse(null);
    }

    @Override
    public Planet savePlanet(Planet planet) {

        SwapiPlanet swapiPlanet = swapiService.getPlanetFromSwapi(planet);
        if (swapiPlanet == null) return null;
        
        int count = swapiPlanet.getFilms().size();
        planet.setFilmsAppearancesCount(count);
        
        return planetRepository.save(planet);
    }
}