package com.meli.xmen.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stats {
    @JsonProperty("count_mutant_dna")
    private int countMutantDna;
    @JsonProperty("count_human_dna")
    private int countHumanDna;
    private double ratio;

    public Stats(int countMutantDna, int countHumanDna) {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
        calculateRatio();
    }

    public void calculateRatio() {
        if (countHumanDna == 0) {
            ratio = 0;
        } else {
            ratio = Math.round(countMutantDna / (double) countHumanDna * 100) / 100.0;
        }
    }
}
