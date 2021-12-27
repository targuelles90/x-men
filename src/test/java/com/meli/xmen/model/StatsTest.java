package com.meli.xmen.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatsTest {

    @Test
    void testAddDnaMutant() {
        Stats stats = new Stats(3,6);
        stats.addDna(true);
        assertEquals(4, stats.getCountMutantDna());
        assertEquals(6, stats.getCountHumanDna());
        double expected = Math.round(4 / (double) 6 * 100) / 100.0;
        assertEquals(expected, stats.getRatio());
    }

    @Test
    void testAddDnaHuman() {
        Stats stats = new Stats(3,6);
        stats.addDna(false);
        assertEquals(3, stats.getCountMutantDna());
        assertEquals(7, stats.getCountHumanDna());
        double expected = Math.round(3 / (double) 7 * 100) / 100.0;
        assertEquals(expected, stats.getRatio());
    }

    @Test
    void given0Humans_thenCalculateRatio() {
        Stats stats = new Stats(3,0);
        assertEquals(3, stats.getCountMutantDna());
        assertEquals(0, stats.getCountHumanDna());
        assertEquals(0.0, stats.getRatio());
    }
}