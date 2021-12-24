package com.meli.xmen.service;

import java.util.ArrayList;
import java.util.List;

public class MutantUtil {

    /**
     * Identify if a dna is mutant or not
     *
     * @param dna dna to be analyzed
     * @param sequenceLength length of the valid sequence
     * @return True is the dna is a mutant, false otherwise
     */
    public static boolean isMutant(String[] dna, int sequenceLength) {
        String[] invertDna = new String[dna.length];
        int dim = dna.length;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < dim; i++) {
            list.add(dna[i]);
            StringBuilder sb = new StringBuilder(dna[i]);
            invertDna[i] = sb.reverse().toString();

            StringBuilder str = new StringBuilder();
            for (String s : dna) {
                str.append(s.charAt(i));
            }
            list.add(str.toString());
        }

        for (int k = 0; k < dim * 2; k++) {
            StringBuilder str = new StringBuilder();
            StringBuilder strInverse = new StringBuilder();
            for (int j = 0; j <= k; j++) {
                int i = k - j;
                if (i < dim && j < dim) {
                    str.append(dna[j].charAt(i));
                    strInverse.append(invertDna[j].charAt(i));
                }
            }
            list.add(str.toString());
            list.add(strInverse.toString());
        }
        long count = list.stream().filter(s -> s.length() >= sequenceLength)
                .filter(str -> maxRepeating(str) >= sequenceLength).count();

        return count > 1;
    }

    protected static int maxRepeating(String str) {
        int maxCount = 0;
        int count = 0;
        int n = str.length();
        for (int i = 0; i < n - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                count++;
            } else {
                count = 0;
            }
            if (count > maxCount) {
                maxCount = count;
            }
        }
        return maxCount + 1;
    }

}
