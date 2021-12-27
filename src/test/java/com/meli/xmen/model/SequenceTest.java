package com.meli.xmen.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SequenceTest {

    @Test
    void testGetSequence() {
        Sequence sequence = new Sequence(0, 0, 3, 2);
        assertEquals(0, sequence.getInitial().getX());
        assertEquals(0, sequence.getInitial().getY());
        assertEquals(3, sequence.getEnd().getX());
        assertEquals(2, sequence.getEnd().getY());
        assertEquals(0.7, sequence.getSlope());
        assertEquals(0, sequence.getC());
    }

    @ParameterizedTest
    @CsvSource({
            "0,0,3,3, 0, 0",
            "0,0,3,3, 2, 2",
            "0,0,3,3, 3, 3",
            "0,3,4,3, 3, 3",
            "0,3,4,3, 1, 3",
            "0,0,0,3, 0, 2"
    })
    void givenSequence_thanInRangeTrue(int xInitial, int yInitial, int xEnd, int yEnd, int x, int y) {
        Sequence sequence = new Sequence(xInitial, yInitial, xEnd, yEnd);
        assertTrue(sequence.isInRange(x, y));
    }

    @ParameterizedTest
    @CsvSource({
            "0,0,3,3, 1, 2",
            "0,0,3,3, 0, 1",
            "0,3,4,3, 1, 4",
            "0,3,4,3, 0, 2",
            "0,0,0,3, 2, 2"
    })
    void givenSequence_thanInRangeFalse(int xInitial, int yInitial, int xEnd, int yEnd, int x, int y) {
        Sequence sequence = new Sequence(xInitial, yInitial, xEnd, yEnd);
        assertFalse(sequence.isInRange(x, y));
    }

    @ParameterizedTest
    @CsvSource({
            "3,0,1,2, 2, 1",
    })
    void givenSequence_thanInInverseRangeRangeTrue(int xInitial, int yInitial, int xEnd, int yEnd, int x, int y) {
        Sequence sequence = new Sequence(xInitial, yInitial, xEnd, yEnd);
        assertTrue(sequence.isInInverseRange(x, y));
    }

    @ParameterizedTest
    @CsvSource({
            "3,0,1,2, 0, 1",
            "3,0,1,2, 3, 1",
    })
    void givenSequence_thanInInverseRangeRangeFalse(int xInitial, int yInitial, int xEnd, int yEnd, int x, int y) {
        Sequence sequence = new Sequence(xInitial, yInitial, xEnd, yEnd);
        assertFalse(sequence.isInInverseRange(x, y));
    }

}