package com.starwars.api.service;

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

import com.starwars.api.model.Planet;
import com.starwars.api.model.SwapiPlanet;
import com.starwars.api.repository.PlanetRepository;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

	@Mock
	private SwapiPlanetService swapiService;
	
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
	
	@Test
	public void savePlanet_shouldReturnNullIfPlanetDoesntExist() {
		lenient().when(swapiService.getPlanetFromSwapi(Mockito.any(Planet.class))).thenReturn(null);
		
		Planet planet = new Planet("planetThatDoesNotExist", "hot", "vulcanoes");
		
		Planet storedPlanet = planetService.savePlanet(planet);
		
		Assertions.assertNull(storedPlanet);
	}
	
	@Test 
	public void savePlanet_shouldReturnTheSavedPlanetWithId() {
		Planet expectedPlanet = new Planet("123", "Kamino", "hot", "jungle", 2);
			
		lenient().when(swapiService.getPlanetFromSwapi(Mockito.any(Planet.class))).then(new Answer<SwapiPlanet>() {

			@Override
			public SwapiPlanet answer(InvocationOnMock invocation) throws Throwable {
				List<String> movieList = new ArrayList<String>();
				movieList.add("movie1");
				movieList.add("movie2");
				
				return new SwapiPlanet(movieList);
			}
			
		});
		lenient().when(planetRepository.save(Mockito.any(Planet.class))).thenReturn(expectedPlanet);
		
		Planet planetToBeSaved = new Planet("Kamino", "hot", "jungle");
		Planet storedPlanet = planetService.savePlanet(planetToBeSaved);
		
		Assertions.assertEquals(expectedPlanet, storedPlanet);
		
	}
	
	@Test
	public void deletePlanet_shouldReturnNull() {
		lenient().when(planetRepository.deleteByIdReturnDeletCount(Mockito.anyString())).thenReturn(0);
		
		String deletedId = planetService.deletePlanet("1");
		
		Assertions.assertNull(deletedId);
	}
	
	@Test
	public void deletePlanet_shouldReturnDeletedId() {
		String idToDelete = "1";
		
		lenient().when(planetRepository.deleteByIdReturnDeletCount(Mockito.anyString())).thenReturn(1);
		
		String deletedId = planetService.deletePlanet(idToDelete);
		
		Assertions.assertEquals(idToDelete, deletedId);
	}
}
