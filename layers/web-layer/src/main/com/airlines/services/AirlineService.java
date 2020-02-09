package com.airlines.services;

import com.airlines.entities.AirlineEntity;
import com.airlines.repositories.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/airline-details")
public class AirlineService {
    private static AirlineRepository repository;

    @Autowired
    public AirlineService(AirlineRepository repository) {
        AirlineService.repository = repository;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    List<AirlineEntity> getAllAirlines() {
        return AirlineService.repository.findAll();
    }

    @RequestMapping(path = "/names", method = RequestMethod.GET)
    List<String> getAllAirlineNames() {
        return getAllAirlines().stream()
                .map(AirlineEntity::getName)
                .collect(Collectors.toList());
    }

    @RequestMapping(path = "/{iata}", method = RequestMethod.GET)
    AirlineEntity getAirlineByIata(@PathVariable String iata) {
        AirlineEntity airline = AirlineService.repository.findByIata(iata);
        return Optional.ofNullable(airline).orElseThrow(() -> new ErrorService(iata));
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> addAirline(@Valid @RequestBody AirlineEntity airline) {
        AirlineService.repository.save(airline);
        return new ResponseEntity<>(null, null, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{iata}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> updateAirline(@Valid @RequestBody AirlineEntity airline, @PathVariable String iata) {
        Optional.ofNullable(AirlineService.repository.findByIata(iata))
                .map((entry) -> {
                    entry.setIata(airline.getIata());
                    entry.setIcao(airline.getIcao());
                    entry.setName(airline.getName());
                    entry.setPhoneNumber(airline.getPhoneNumber());
                    return entry;
                })
                .orElseGet(() -> {
                    AirlineService.repository.save(airline);
                    return airline;
                });

        return new ResponseEntity<>(null, null, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{iata}", method = RequestMethod.DELETE)
    ResponseEntity<?> removeAirline(@PathVariable String iata) {
        AirlineService.repository.delete(AirlineService.repository.findByIata(iata));
        return new ResponseEntity<>(null, null, HttpStatus.CREATED);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorService handleException(MethodArgumentNotValidException exception) {
        return new ErrorService(exception.getMessage());
    }
}
