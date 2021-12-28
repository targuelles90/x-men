package com.meli.xmen.api.v1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.xmen.api.v1.dto.HumanDto;
import com.meli.xmen.model.Stats;
import com.meli.xmen.service.MutantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class})
@WebMvcTest(controllers = XMenController.class)
class XMenControllerTest {
    private static final String[] DNA = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private XMenController controller;

    @MockBean
    private MutantService service;

    @ParameterizedTest
    @ValueSource(strings = {"ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG", "ATG,CAG,TTA"})
    void givenValidDna_thenReturnOk(String dna) throws Exception {
        Mockito.when(service.verifyMutant(Mockito.any())).thenReturn(true);
        HumanDto dto = new HumanDto();
        dto.setDna(dna.split(","));

        mockMvc.perform(post("/v1/mutant").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        Mockito.verify(service, Mockito.times(1)).verifyMutant(Mockito.any());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ATGC,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG", "ATG,TTA", "WER,ASD,QWE", "ASA,CCC,TTT"})
    void givenInValidDna_thenReturnBadRequest(String dna) throws Exception {
        Mockito.when(service.verifyMutant(Mockito.any())).thenReturn(true);
        HumanDto dto = new HumanDto();
        dto.setDna(dna.split(","));

        mockMvc.perform(post("/v1/mutant").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());

        Mockito.verify(service, Mockito.never()).verifyMutant(Mockito.any());
    }

    @Test
    void givenNotMutantDna_thenReturnForbidden() throws Exception {
        Mockito.when(service.verifyMutant(Mockito.any())).thenReturn(false);
        HumanDto dto = new HumanDto();
        dto.setDna(DNA);

        mockMvc.perform(post("/v1/mutant").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isForbidden());

        Mockito.verify(service, Mockito.times(1)).verifyMutant(Mockito.any());
    }

    @Test
    void getStats() throws Exception {
        Stats stats = new Stats(3,7);
        Mockito.when(service.getStats()).thenReturn(stats);
        mockMvc.perform(get("/v1/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count_mutant_dna", is(3)))
                .andExpect(jsonPath("$.count_human_dna", is(7)))
                .andExpect(jsonPath("$.ratio", is(0.43)));
        Mockito.verify(service, Mockito.times(1)).getStats();
    }

}