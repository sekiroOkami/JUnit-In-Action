package com.manning.junitbook.databases.model;


import com.manning.junitbook.databases.model.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountriesHibernateTest {
    private EntityManagerFactory emf;
    private EntityManager em;
    private List<Country> expectedCountryList = new ArrayList<>();
    private List<Country> expectedCountryListStartWithA = new ArrayList<>();
    public static final String[][] COUNTRY_INIT_DATA = {{"Australia", "AU"}, {"Canada", "CA"}, {"France", "FR"},
            {"Germany", "DE"}, {"Italy", "IT"}, {"Japan", "JP"}, {"Romania", "RO"},
            {"Russian Federation", "RU"}, {"Spain", "ES"}, {"Switzerland", "CH"},
            {"United Kingdom", "UK"}, {"United States", "US"}};

    @BeforeEach
    void setUp() {
        initExpectedCountryLists();
        emf = Persistence.createEntityManagerFactory("manning.hibernate");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Arrays.stream(COUNTRY_INIT_DATA)
                .forEach(strings -> {
                    var country = new Country(strings[0], strings[1]);
                    em.persist(country);
                });
        em.getTransaction().commit();
    }

    @Test
    void testCountryList() {
        List<Country> countryList = em.createQuery(
                "select c from Country c"
        ).getResultList();
        assertNotNull(countryList);
        assertEquals(COUNTRY_INIT_DATA.length, countryList.size());
        IntStream.range(0, expectedCountryList.size())
                .forEachOrdered(i -> {
                    Country expectedCountry = expectedCountryList.get(i);
                    Country actualCountry =  countryList.get(i);
                    assertEquals(expectedCountry, actualCountry);
                });
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    private void initExpectedCountryLists() {
        Arrays.stream(COUNTRY_INIT_DATA)
                .forEach(strings -> {
                    Country country = new Country(strings[0], strings[1]);
                    expectedCountryList.add(country);
                    if (country.getName().startsWith("A")) {
                        expectedCountryListStartWithA.add(country);
                    }
                });
    }
}
