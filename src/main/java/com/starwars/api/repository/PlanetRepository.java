package com.starwars.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.starwars.api.model.Planet;

@RepositoryRestResource(collectionResourceRel = "planet", path = "planet")
public interface PlanetRepository extends MongoRepository<Planet, String> {

    @Query("{ 'name' : { $regex : ?0, $options: 'i'} }")
    List<Planet> findByName(String regexp);
}