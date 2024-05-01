package com.manning.junitbook.spring.controller;

import com.manning.junitbook.spring.model.Country;
import com.manning.junitbook.spring.model.Passenger;
import com.manning.junitbook.spring.model.PassengerNotFoundException;
import com.manning.junitbook.spring.model.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PassengerController {
    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private Map<String, Country> countriesMap;

    @GetMapping("/passengers")
    List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @PostMapping("/passengers")
    @ResponseStatus(HttpStatus.CREATED)
    Passenger createPassenger(
            @RequestBody Passenger passenger
    ) {
        return passengerRepository.save(passenger);
    }

    @GetMapping("/passengers/{id}")
    Passenger findPassenger(@PathVariable Long id) {
        return passengerRepository.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException(id));
    }

    @PatchMapping("/passengers/{id}")
    Passenger patchPassenger(@RequestBody Map<String, String> updates, @PathVariable Long id) {
        return passengerRepository.findById(id).map(passenger -> {
            String name = updates.get("name");
            if (name != null) {
                passenger.setName(name);
            }
            Country country = countriesMap.get(updates.get("country"));
            if (null != country) {
                passenger.setCountry(country);
            }
            String isRegistered = updates.get("isRegistered");
            if (isRegistered != null) {
                passenger.setIsRegistered(isRegistered.equalsIgnoreCase("true") ? true: false);
            }
            return passengerRepository.save(passenger);
        }).orElseThrow(() -> {throw new PassengerNotFoundException(id);});
    }

    @DeleteMapping("/passengers/{id}")
    void deletePassenger(@PathVariable Long id) {
        passengerRepository.deleteById(id);
    }
}
