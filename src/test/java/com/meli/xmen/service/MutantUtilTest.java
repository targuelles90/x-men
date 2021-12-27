package com.meli.xmen.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MutantUtilTest {

    @ParameterizedTest
    @CsvSource({
            "ATGCGA-CAGTGC-TTATGT-AGAAGG-CCCCTA-TCACTG, 4",
            "ACAAGA-ACGTGC-ATATGT-AGAAGG-CCCCTA-TCACTG, 4",
            "ABC-CAB-AAA, 3",
            "AAA-CBB-AAA, 3",
            "ABA-ABB-ABA, 3",
            "DBCD-AABD-CBAB-ABCA, 3",
            "ABCD-ABAD-CADB-ADCA, 3",
            "TTTCCC-CAGTGC-TTATGT-AGAAGG-CCCCTA-TCACTG, 3",
    })
    void givenMutantDna_thenReturnTrue(String dna, int sequence) {
        String[] input = dna.split("-");
        assertTrue(MutantUtil.isMutant(input, sequence, 1));
    }

    @ParameterizedTest
    @CsvSource({
            "ATGCGA-CCGTGC-TTATAT-AGAAGG-CACCTA-TCACTG, 4",
            "ACAAGA-ACGTGC-TTATGT-AGAAGG-CTCTTA-TCACTG, 4",
            "AQWWR-CAGTG-TATGT-WERSD-QAZXS, 4",
    })
    void givenMutantDna_thenReturnFalse(String dna, int sequence) {
        String[] input = dna.split("-");
        assertFalse(MutantUtil.isMutant(input, sequence, 1));
    }
}