package com.meli.xmen.service;

import com.meli.xmen.model.Sequence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntBinaryOperator;

public class MutantUtil {
    private static final String HORIZONTAL = "horizontal";
    private static final String VERTICAL = "vertical";
    private static final String DIAGONAL = "diagonal";
    private static final String DIAGONAL_INVERSE = "diagonal_inverse";

    /**
     * Identify if a dna is mutant or not
     *
     * @param dna               Dna to be analyzed
     * @param sequenceLength    Length of the valid sequence
     * @return                  True is the dna is a mutant, false otherwise
     */
    public static boolean isMutant(String[] dna, int sequenceLength) {
        Map<String, List<Sequence>> map = new HashMap<>();
        map.put(HORIZONTAL, new ArrayList<>());
        map.put(VERTICAL, new ArrayList<>());
        map.put(DIAGONAL, new ArrayList<>());
        map.put(DIAGONAL_INVERSE, new ArrayList<>());

        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna.length; j++) {
                //HORIZONTAL
                IntBinaryOperator search = (x, y) -> searchHorizontal(dna, x, y);
                ThreeFunction<Integer, Sequence> createNewSequence = (x, y, c) -> new Sequence(x, y, x, y + c);
                findSequence(i, j, sequenceLength, map.get(HORIZONTAL), Sequence::isInRange, search, createNewSequence);
                if (count(map) >= 2) {
                    return true;
                }

                //VERTICAL
                search = (x, y) -> searchVertical(dna, x, y);
                createNewSequence = (x, y, c) -> new Sequence(x, y, x + c, y);
                findSequence(i, j, sequenceLength, map.get(VERTICAL), Sequence::isInRange, search, createNewSequence);
                if (count(map) >= 2) {
                    return true;
                }

                //DIAGONAL
                search = (x, y) -> searchDiagonal(dna, x, y);
                createNewSequence = (x, y, c) -> new Sequence(x, y, x + c, y + c);
                findSequence(i, j, sequenceLength, map.get(DIAGONAL), Sequence::isInRange, search, createNewSequence);
                if (count(map) >= 2) {
                    return true;
                }

                //DIAGONAL_INVERSE
                search = (x, y) -> searchDiagonalInverse(dna, x, y);
                createNewSequence = (x, y, c) -> new Sequence(x + c, y - c, x, y);
                findSequence(i, j, sequenceLength, map.get(DIAGONAL_INVERSE), Sequence::isInInverseRange, search, createNewSequence);
                if (count(map) >= 2) {
                    return true;
                }
            }
        }
        return false;
    }

    static int count(Map<String, List<Sequence>> map) {
        return map.get(HORIZONTAL).size() + map.get(VERTICAL).size() + map.get(DIAGONAL).size() + map.get(DIAGONAL_INVERSE).size();
    }

    static void findSequence(int i, int j, int sequenceLength, List<Sequence> sequences, ThreeFunction<Sequence, Boolean> condition, IntBinaryOperator custom, ThreeFunction<Integer, Sequence> ss) {
        if (sequences.stream().anyMatch(s -> condition.apply(s, i, j))) return;
        int count = custom.applyAsInt(i, j);
        if (count >= sequenceLength - 1)
            sequences.add(ss.apply(i, j, count));
    }

    static int searchHorizontal(String[] dna, int x, int y) {
        int count = 0;
        for (int i = y; i < dna.length - 1; i++) {
            if (dna[x].charAt(i) == dna[x].charAt(i + 1))
                count++;
            else
                break;
        }
        return count;
    }

    static int searchVertical(String[] dna, int x, int y) {
        int count = 0;
        for (int i = x; i < dna.length - 1; i++) {
            if (dna[i].charAt(y) == dna[i + 1].charAt(y))
                count++;
            else
                break;
        }
        return count;
    }

    static int searchDiagonal(String[] dna, int x, int y) {
        int count = 0;
        for (int i = x, j = y; i < dna.length - 1 && j < dna.length - 1; i++, j++) {
            if (dna[i].charAt(j) == dna[i + 1].charAt(j + 1))
                count++;
            else
                break;
        }
        return count;
    }

    static int searchDiagonalInverse(String[] dna, int x, int y) {
        int count = 0;
        for (int i = x, j = y; i < dna.length - 1 && j > 0; i++, j--) {
            if (dna[i].charAt(j) == dna[i + 1].charAt(j - 1))
                count++;
            else
                break;
        }
        return count;
    }
}
