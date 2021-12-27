package com.meli.xmen.api.v1.controller;

import com.meli.xmen.api.v1.dto.HumanDto;
import com.meli.xmen.model.Stats;
import com.meli.xmen.service.MutantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
@Validated
public class XMenController {
    @Autowired
    private MutantService service;

    @Operation(description = "It allows to verify if the dna belongs to a mutant or not. If it is a mutant it returns response status 200 otherwise it returns a 403.")
    @PostMapping(path = "/mutant", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Is a Mutant"),
            @ApiResponse(responseCode = "403", description = "Is not a Mutant"),
    })
    @ResponseBody
    public ResponseEntity<Void> verifyMutant(@Valid @RequestBody HumanDto dto) {
        boolean isMutant = service.verifyMutant(dto.getDna());
        if (isMutant)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Operation(description = "Retrieve stats.")
    @GetMapping(path = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Stats> stats() {
        return new ResponseEntity<>(service.getStats(), HttpStatus.OK);
    }

}
