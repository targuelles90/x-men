package com.meli.xmen.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HumanTest {

    @ParameterizedTest
    @ValueSource(strings = {"ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG", "ASD,ASD,ASD"})
    void setDna(String dna) {
        String[] dnaArray = dna.split(",");
        Human human = new Human(dnaArray);
        assertEquals(dna, human.getDna());
        assertNotNull(human.getId());
    }
}