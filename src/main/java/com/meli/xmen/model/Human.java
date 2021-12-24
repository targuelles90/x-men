package com.meli.xmen.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.DigestUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.nio.charset.StandardCharsets;

@Entity
@Getter
@Setter
public class Human {
    @Id
    private String id;
    @Column
    private String dna;
    @Column
    private boolean isMutant;

    public Human() {
    }

    public Human(String[] dna) {
        this.setDna(dna);
    }

    public void setDna(String[] dna) {
        this.dna = String.join(",", dna);
        this.id = DigestUtils.md5DigestAsHex(this.dna.getBytes(StandardCharsets.UTF_8));
    }
}
