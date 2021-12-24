package com.meli.xmen.service;

import com.meli.xmen.model.Human;
import com.meli.xmen.repository.HumanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MutantService {

    @Value("${xmen.mutant.dna.size:4}")
    private int dnaSize;

    private final HumanRepository repository;

    public boolean verifyMutant(String[] dna) {
        Human human = new Human(dna);
        if (repository.existsById(human.getId()))
            return repository.getById(human.getId()).isMutant();
        boolean isMutant = MutantUtil.isMutant(dna, dnaSize);
        human.setMutant(isMutant);
        repository.save(human);
        return isMutant;
    }
}
