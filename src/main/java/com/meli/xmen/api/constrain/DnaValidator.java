package com.meli.xmen.api.constrain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DnaValidator implements ConstraintValidator<DnaConstrain, String[]> {

    @Override
    public boolean isValid(String[] list, ConstraintValidatorContext context) {
        if (list == null) return false;

        for (String dna : list) {
            if (dna == null || dna.length() != list.length) return false;

            for (int i = 0; i < dna.length(); i++) {
                char c = dna.charAt(i);
                if (c != 'A' && c != 'T' && c != 'C' && c != 'G') return false;
            }
        }

        return true;
    }
}
