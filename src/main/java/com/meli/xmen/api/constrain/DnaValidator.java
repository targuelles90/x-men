package com.meli.xmen.api.constrain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class DnaValidator implements ConstraintValidator<DnaConstrain, String[]> {
    private final List<Character> validChars = new ArrayList<>(List.of('A', 'C', 'G', 'T'));

    @Override
    public boolean isValid(String[] list, ConstraintValidatorContext context) {
        if (list == null) return false;

        for (String dna : list) {
            if (dna == null || dna.length() != list.length) return false;

            for (int i = 0; i < dna.length(); i++) {
                char c = dna.charAt(i);
                if (!validChars.contains(c)) return false;
            }
        }

        return true;
    }
}
