package com.airlines.repositories;

import com.airlines.entities.AirlineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<AirlineEntity, Long> {
    AirlineEntity findByIata(String username);
}
