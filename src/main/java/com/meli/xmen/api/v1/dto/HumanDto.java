package com.meli.xmen.api.v1.dto;

import com.meli.xmen.api.constrain.DnaConstrain;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HumanDto {
    @DnaConstrain
    @ArraySchema( schema = @Schema(title = "DNA", description = "DNA of the human"),
            arraySchema = @Schema(type = "string", example = "[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]"))
    private String[] dna;

}
