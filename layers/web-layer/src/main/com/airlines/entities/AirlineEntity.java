package com.airlines.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AirlineEntity {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @NotBlank(message = "iata name must not be blank!")
    private String iata;
    @NotBlank(message = "icao name must not be blank!")
    private String icao;
    @NotBlank(message = "name name must not be blank!")
    private String name;
    @NotBlank(message = "phoneNumber name must not be blank!")
    private String phoneNumber;

    public AirlineEntity(String iata, String icao, String name, String phoneNumber) {
        this.iata = iata;
        this.icao = icao;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public AirlineEntity() { // jpa only
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
