package com.manning.junitbook.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manning.junitbook.spring.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TestingRestApiApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private Flight flight;

	@Autowired
	private Map<String, Country> countriesMap;

	@MockBean
	private PassengerRepository passengerRepository;

	@MockBean
	private CountryRepository countryRepository;

	@Test
	void testGetAllCountries() throws Exception {
		when(countryRepository.findAll()).thenReturn(
				new ArrayList<>(countriesMap.values())
		);
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
				NestedServletException.class,
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


}
