package com.meli.xmen.repository;

import com.meli.xmen.model.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HumanRepository extends JpaRepository<Human, String> {

    @Query("SELECT COUNT(h) FROM Human h WHERE h.isMutant = true")
    int countMutants();

    @Query("SELECT COUNT(h) FROM Human h WHERE h.isMutant = false")
    int countHumans();

}