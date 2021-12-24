package com.meli.xmen.api.v1.dto;

import com.meli.xmen.api.constrain.DnaConstrain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HumanDto {

    @DnaConstrain
    private String[] dna;
}
