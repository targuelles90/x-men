package com.meli.xmen.repository;

import com.meli.xmen.model.Human;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HumanRepository extends JpaRepository<Human, String> {
}
