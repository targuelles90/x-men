package com.meli.xmen.api.v1.controller;

import com.meli.xmen.api.v1.dto.HumanDto;
import com.meli.xmen.service.MutantService;
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

    @PostMapping(path = "/mutant", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Void> verifyMutant(@Valid @RequestBody HumanDto dto) {
        boolean isMutant = service.verifyMutant(dto.getDna());
        if(isMutant)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
