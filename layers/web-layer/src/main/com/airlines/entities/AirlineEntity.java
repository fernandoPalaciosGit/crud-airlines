package com.airlines.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AirlineEntity {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    private String iata;
    private String icao;
    private String name;
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
