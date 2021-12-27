package com.meli.xmen.service;

import com.meli.xmen.model.Human;
import com.meli.xmen.model.Stats;
import com.meli.xmen.repository.HumanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class MutantServiceTest {
    private static final String[] DNA = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

    @Mock
    HumanRepository repository;

    @Mock
    CacheManager cacheManager;

    @Autowired
    MutantService service;

    @Mock
    private Cache cache;

    @Mock
    private Cache.ValueWrapper wrapper;


    @BeforeEach
    void setUp() {
        service = new MutantService(repository, cacheManager);
    }

    @Test
    void givenNotExistHuman_thenVerifyAndSave() {
        Mockito.when(cacheManager.getCache(any())).thenReturn(cache);
        Mockito.when(cache.get(any())).thenReturn(wrapper);
        Mockito.when(wrapper.get()).thenReturn(null);

        Mockito.when(repository.existsById(any())).thenReturn(false);
        Mockito.when(repository.save(any())).thenReturn(new Human());
        try (MockedStatic<MutantUtil> mocked = Mockito.mockStatic(MutantUtil.class)) {
            mocked.when(() -> MutantUtil.isMutant(any(), anyInt())).thenReturn(true);
            boolean isMutant = service.verifyMutant(DNA);
            assertTrue(isMutant);
        }
        Mockito.verify(repository, Mockito.times(1)).existsById(any());
        Mockito.verify(repository, Mockito.never()).getById(any());
        Mockito.verify(repository, Mockito.times(1)).save(any());
    }

    @Test
    void givenExistHuman_thenReturnIt() {
        Human human = new Human();
        human.setMutant(false);
        Mockito.when(repository.existsById(any())).thenReturn(true);
        Mockito.when(repository.getById(any())).thenReturn(human);

        boolean isMutant = service.verifyMutant(DNA);
        assertFalse(isMutant);

        Mockito.verify(repository, Mockito.times(1)).existsById(any());
        Mockito.verify(repository, Mockito.times(1)).getById(any());
        Mockito.verify(repository, Mockito.never()).save(any());
    }

    @Test
    void givenCachedHuman_thenReturnIt() {
        Mockito.when(cacheManager.getCache(any())).thenReturn(cache);
        Mockito.when(cache.get(any())).thenReturn(wrapper);
        Mockito.when(wrapper.get()).thenReturn(false);

        Human human = new Human();
        human.setMutant(false);

        boolean isMutant = service.verifyMutant(DNA);
        assertFalse(isMutant);

        Mockito.verify(repository, Mockito.never()).existsById(any());
        Mockito.verify(repository, Mockito.never()).getById(any());
        Mockito.verify(repository, Mockito.never()).save(any());
    }

    @Test
    void getStats_thenOk() {
        Human human = new Human();
        human.setMutant(false);
        Mockito.when(repository.countMutants()).thenReturn(2);
        Mockito.when(repository.countHumans()).thenReturn(4);
        Stats stats = service.getStats();

        assertEquals(2, stats.getCountMutantDna());
        assertEquals(4, stats.getCountHumanDna());
        assertEquals(2 / (float) 4, stats.getRatio());

        Mockito.verify(repository, Mockito.times(1)).countMutants();
        Mockito.verify(repository, Mockito.times(1)).countHumans();
    }

    @Test
    void givenStats_thenUpdateCacheStats() {
        Mockito.when(cacheManager.getCache(any())).thenReturn(cache);
        Mockito.when(cache.get(any())).thenReturn(wrapper);
        Mockito.when(wrapper.get()).thenReturn(new Stats(2, 3));

        service.updateCacheStats(true);
        Stats stats = (Stats) wrapper.get();
        Mockito.verify(cache, Mockito.times(1)).put(any(), any());
        assertNotNull(stats);
        assertEquals(3, stats.getCountMutantDna());
        assertEquals(3, stats.getCountHumanDna());
        assertEquals(1.0, stats.getRatio());
    }

    @Test
    void givenNullStats_thenNotUpdateCacheStats() {
        Mockito.when(cacheManager.getCache(any())).thenReturn(cache);
        Mockito.when(cache.get(any())).thenReturn(wrapper);
        Mockito.when(wrapper.get()).thenReturn(null);

        service.updateCacheStats(true);
        assertNull(wrapper.get());
        Mockito.verify(cache, Mockito.never()).put(any(), any());
    }


}