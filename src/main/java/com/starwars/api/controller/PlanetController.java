package com.starwars.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.starwars.api.model.Planet;
import com.starwars.api.service.PlanetService;

@RestController
@RequestMapping("/planets")
public class PlanetController {
    
    @Autowired
    private PlanetService planetService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Planet>> getAllPlanets() {
        List<Planet> foundPlanets = planetService.findAll();

        return ResponseEntity.ok(foundPlanets);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Planet> getPlanetById(@PathVariable("id") String id) {
        Planet foundPlanet = planetService.findById(id);
        
        if (foundPlanet == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(foundPlanet);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Planet>> getPlanetsByName(@RequestParam String name) {
        List<Planet> foundPlanets = planetService.findByName(name);
        
        if (foundPlanets.size() == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(foundPlanets);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Planet> savePlanet(@RequestBody Planet planet) {
        Planet createdPlanet = planetService.savePlanet(planet);

        if (createdPlanet == null) {
            return ResponseEntity.badRequest().build();
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(createdPlanet.getId())
          .toUri();

        return ResponseEntity.created(uri).body(createdPlanet);
    }
}