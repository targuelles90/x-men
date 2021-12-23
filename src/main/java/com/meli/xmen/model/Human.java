package com.meli.xmen.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Human {
    @Id
    private String id;
    @Column
    private String adn;
    @Column
    private boolean isMutant;
}
