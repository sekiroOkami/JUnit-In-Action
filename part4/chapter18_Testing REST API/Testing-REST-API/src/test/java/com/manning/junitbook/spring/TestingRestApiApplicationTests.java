package com.manning.junitbook.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manning.junitbook.spring.beans.FlightBuilder;
import com.manning.junitbook.spring.model.*;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @SpringBootTest searches the current package of the test class and its subpackages for bean definition
@SpringBootTest
// enable all autoconfiguration related to the MockMvc objects used in the test
@AutoConfigureMockMvc
// creates a flight bean and a countriesMap bean
@Import(FlightBuilder.class)
class TestingRestApiApplicationTests {

	@Autowired
	// MockMvc is the main entry point for serverside Spring REST application testing
	// REST operation will perform against this MockMvc object during the tests
	private MockMvc mvc;

	@Autowired
	// this field was injected from FLightBuilder
	private Flight flight;

	@Autowired
	// this field was injected from FLightBuilder
	private Map<String, Country> countriesMap;

	@MockBean
	// MockBen is used to add mock objects to the Spring application context:
	// the mock will replace any existing bean of the same type in the application context.
	// The operator will provide instructions for the behavior of the mock objects during the tests.
	private PassengerRepository passengerRepository;

	@MockBean
	private CountryRepository countryRepository;

	@Test
	void testGetAllCountries() throws Exception {
		when(countryRepository.findAll()).thenReturn(
				new ArrayList<>(countriesMap.values())
		);
		// simulate the execution of the GET method on the /countries URL
		mvc.perform(get("/countries"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				// '$' symbol presents the root of the JSON document
				.andExpect(jsonPath("$", hasSize(3)));
		// verify that the findAll has been executed exactly once on the countryRepository bean
		verify(countryRepository, times(1)).findAll();
	}

	@Test
	void testGetAllPassengers() throws Exception {
		when(passengerRepository.findAll()).thenReturn(
				new ArrayList<>(flight.getPassengers()));
		mvc.perform(get("/passengers"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(20)));
		verify(passengerRepository, times(1)).findAll();
	}

	@Test
	void testPassengerNotFound() {
		Throwable throwable = assertThrows(
				ServletException.class,
				() -> mvc.perform(get("/passengers/30"))
						.andExpect(status().isNotFound())
		);
		assertEquals(PassengerNotFoundException.class, throwable.getCause().getClass());
	}

	@Test
	void testPostPassenger() throws Exception {
        Passenger passenger = new Passenger("Sekiro Okami");
        passenger.setCountry(countriesMap.get("US"));
        passenger.setIsRegistered(false);
        when(passengerRepository.save(passenger)).thenReturn(passenger);
        mvc.perform(post("/passengers")
				// ObjectMapper, which is the main class of the Jackson library
				// offers functionality for reading and writing JSON to and from basic POJOs
                        .content(new ObjectMapper()
                        .writeValueAsString(passenger))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Sekiro Okami")))
                .andExpect(jsonPath("$.country.codeName", is("US")))
                .andExpect(jsonPath("$.country.name", is("USA")))
                .andExpect(jsonPath("$.registered", is(Boolean.FALSE)));
        verify(passengerRepository, times(1)).save(passenger);
	}

	@Test
	void testPatchPassenger() throws Exception {
		var passsenger = new Passenger("Sophia Graham");
		passsenger.setCountry(countriesMap.get("UK"));
		passsenger.setIsRegistered(false);
		when(passengerRepository.findById(1L)).thenReturn(Optional.of(passsenger));
		when(passengerRepository.save(passsenger)).thenReturn(passsenger);

		// sets a JSON object named updates,
		String updates = "{\"name\":\"Sophia Jones\", \"country\":\"AU\",\"isRegistered\":\"true\"}";
		mvc.perform(patch("/passengers/1")
				.content(updates)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		verify(passengerRepository, times(1)).findById(1L);
		verify(passengerRepository, times(1)).save(passsenger);
	}

	@Test
	public void testDeletePassenger() throws Exception {
		mvc.perform(delete("/passengers/4"))
				.andExpect(status().isOk());
		verify(passengerRepository, times(1)).deleteById(4L);
	}

}
