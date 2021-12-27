package com.meli.xmen.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class Stats {
    @JsonProperty("count_mutant_dna")
    @Schema(description = "Count of mutants")
    private int countMutantDna;
    @JsonProperty("count_human_dna")
    @Schema(description = "Count of humans")
    private int countHumanDna;
    @Schema(description = "Ratio of mutants to humans. If there are no humans, the ratio is 0. ")
    private double ratio;

    public Stats(int countMutantDna, int countHumanDna) {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
        calculateRatio();
    }

    public Stats() {
    }

    public void setCountMutantDna(int countMutantDna) {
        this.countMutantDna = countMutantDna;
        calculateRatio();
    }

    public void setCountHumanDna(int countHumanDna) {
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

    public void addDna(boolean isMutant) {
        if (isMutant)
            countMutantDna++;
        else
            countHumanDna++;
        calculateRatio();
    }
}
