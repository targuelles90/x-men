package com.meli.xmen.service;

import com.meli.xmen.model.Human;
import com.meli.xmen.model.Stats;
import com.meli.xmen.repository.HumanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MutantService {
    private static final String STATS = "stats";
    private final HumanRepository repository;
    private final CacheManager cacheManager;

    @Value("${xmen.mutant.dna.size:4}")
    private int dnaSize;

    @Value("${xmen.mutant.min_sequences:1}")
    private int minSequences;

    public boolean verifyMutant(String[] dna) {
        Human human = new Human(dna);
        Cache cache = cacheManager.getCache("humans");
        if (cache != null && cache.get(human.getId()) != null) {
            Cache.ValueWrapper valueWrapper = cache.get(human.getId());
            if (valueWrapper != null && valueWrapper.get() != null) {
                return (boolean) valueWrapper.get();
            }
        }
        if (repository.existsById(human.getId()))
            return repository.getById(human.getId()).isMutant();
        boolean isMutant = MutantUtil.isMutant(dna, dnaSize, minSequences);
        human.setMutant(isMutant);
        repository.save(human);
        if (cache != null) {
            cache.put(human.getId(), isMutant);
        }
        updateCacheStats(isMutant);

        return isMutant;
    }

    @Cacheable(value = "stats", key = "'stats'")
    public Stats getStats() {
        int mutants = repository.countMutants();
        int humans = repository.countHumans();
        return new Stats(mutants, humans);
    }

    void updateCacheStats(boolean isMutant) {
        Cache cacheStats = cacheManager.getCache(STATS);
        if (cacheStats != null && cacheStats.get(STATS) != null) {
            Cache.ValueWrapper valueWrapper = cacheStats.get(STATS);
            if (valueWrapper != null) {
                Stats stats = (Stats) valueWrapper.get();
                if (stats != null) {
                    stats.addDna(isMutant);
                    cacheStats.put(STATS, stats);
                }
            }
        }
    }
}
