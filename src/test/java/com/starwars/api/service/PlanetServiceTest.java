package com.starwars.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.starwars.api.model.Planet;
import com.starwars.api.repository.PlanetRepository;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

	@Mock
	private RestTemplate restTemplate;
	
	@Mock
	private PlanetRepository planetRepository;
	
	@InjectMocks
	@Autowired
	private PlanetServiceImpl planetService;
	
	@BeforeEach
	void init() {		
		lenient().when(planetRepository.findByName("al")).then(new Answer<List<Planet>>() {

			@Override
			public List<Planet> answer(InvocationOnMock invocation) throws Throwable {
				List<Planet> planetList = new ArrayList<Planet>();
				
				planetList.add(new Planet("1", "Alderaan", "temperate", "grasslands", 2));
				planetList.add(new Planet("2", "Saleucami", "hot", "caves", 1));
				
				return planetList;
			}
			
		});
		
		lenient().when(planetRepository.findById(Mockito.anyString())).thenReturn(Optional.of(new Planet("123", "Kamino", "hot", "jungle", 1)));
	}
	
	@Test
	public void planetServiceShouldNotBeNull() {
		Assertions.assertNotNull(planetService);
	}
	
	@Test
	public void findById_shouldReturnAPlanet() {
		String id = "123";
		Planet foundPlanet = planetService.findById(id);
		
		Assertions.assertNotNull(foundPlanet);
		Assertions.assertEquals(id, foundPlanet.getId());
	}
	
	@Test
	public void findByName_shouldReturnAListOfPlanets() {
		String name = "al";
		List<Planet> planetsFound = planetService.findByName(name);
		
		List<Planet> planetList = new ArrayList<Planet>();
		
		planetList.add(new Planet("1", "Alderaan", "temperate", "grasslands", 2));
		planetList.add(new Planet("2", "Saleucami", "hot", "caves", 1));
		
		Assertions.assertNotNull(planetsFound);
		Assertions.assertIterableEquals(planetsFound, planetList);
	}
}
