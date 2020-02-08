package com.airlines.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AirlineService {
    // todo: connect JPA
    private static AirlineRepository repository;

    public static AirlineRepository getRepository() {
        return AirlineService.repository;
    }

    @Autowired
    public AirlineService(AirlineRepository repository) {
        AirlineService.repository = repository;
    }

    @RequestMapping(value = "/airline-details", method = RequestMethod.GET)
    List<AirlineEntity> getAllAirlines() {
        return AirlineService.repository.findAll();
    }

    @RequestMapping(value = "/airline-details/{id}", method = RequestMethod.GET)
    AirlineEntity getAirlineByName(@PathVariable String id) {
        Validator.validateAirline(AirlineService.repository, id);
        return AirlineService.repository.findByAirlineId(id).get();
    }

    @RequestMapping(value = "/airline-details", method = RequestMethod.POST)
    ResponseEntity<?> addAirline(@RequestBody AirlineEntity instructor) {
        AirlineService.repository.save(instructor);
        return new ResponseEntity<>(null, null, HttpStatus.CREATED);
    }
}
