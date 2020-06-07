package com.starwars.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starwars.api.model.Planet;
import com.starwars.api.model.SwapiPlanet;
import com.starwars.api.model.SwapiResult;
import com.starwars.api.repository.PlanetRepository;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository planetRepository;
    @Autowired
    private RestTemplate restTemplate;
    private final String SWAPI_URL_PLANETS_SEARCH = "https://swapi.dev/api/planets/?search=";

    public PlanetServiceImpl() {

    }

    public PlanetServiceImpl(PlanetRepository planetRepository, RestTemplate restTemplate) {
        this.planetRepository = planetRepository;
        this.restTemplate = restTemplate;
    }

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

        SwapiPlanet swapiPlanet = getPlanetFromSwapi(planet);
        if (swapiPlanet == null) return null;
        
        int count = swapiPlanet.getFilms().size();
        planet.setFilmsAppearancesCount(count);
        
        return planetRepository.save(planet);
    }

    public SwapiPlanet getPlanetFromSwapi(Planet planet) {
        if (planet == null) return null;

        ResponseEntity<String> swapiResponse = restTemplate
            .getForEntity(SWAPI_URL_PLANETS_SEARCH + planet.getName(), String.class);

        ObjectMapper mapper = new ObjectMapper();
        SwapiPlanet firstPlanetFound = null;

        if (swapiResponse != null) {
	        try {
	            SwapiResult result = mapper.readValue(swapiResponse.getBody(), SwapiResult.class);
	            
	            if (result.getPlanetList().size() > 0) {
	            	firstPlanetFound = result.getPlanetList().get(0);
	            }
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
        }

        return firstPlanetFound;
    }
}