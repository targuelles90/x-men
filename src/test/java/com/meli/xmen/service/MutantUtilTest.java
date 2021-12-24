package com.meli.xmen.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MutantUtilTest {

    @ParameterizedTest
    @CsvSource({"TTATT, 2", "GGGA, 3", "CAGTGC, 1", "AATAG, 2", "CCCCTA, 4", "TCACTG, 1"})
    void countConsecutive(String dna, int expected) {
        int sequence = MutantUtil.maxRepeating(dna);
        assertEquals(expected, sequence);
    }

    @Test
    void givenMutantDna_thenReturnTrue() {
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        assertTrue(MutantUtil.isMutant(dna, 4));
    }

    @Test
    void givenNotMutantDna_thenReturnFalse() {
        String[] dna = {"AQWWR","CAGTG","WERSD","QAZXS","HYUJK"};
        assertFalse(MutantUtil.isMutant(dna, 4));
    }
}