package co.com.mercadolibre.usecase.mutante;

import co.com.mercadolibre.model.mutante.Mutante;
import co.com.mercadolibre.model.mutante.gateways.MutanteRepository;
import co.com.mercadolibre.model.stats.Stats;
import co.com.mercadolibre.usecase.stats.StatsUseCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MutantUseCaseTest {

    @Mock
    private MutanteRepository repository;
    private MutantUseCase useCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.useCase = new MutantUseCase(repository);
    }

    @Test
    public void isMutant() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        when(repository.createMutant(any())).thenReturn(new Mutante());
        boolean isMutant = useCase.isMutant(dna);
        assertEquals(true,isMutant);
    }

    @Test
    public void isNotMutant() {
        String[] dna = {"TTGCGA", "CCGTGC", "TTATGT", "AGAAGG", "GCCCTA", "TCACTG"};
        when(repository.createMutant(any())).thenReturn(new Mutante());
        boolean isMutant = useCase.isMutant(dna);
        assertEquals(false,isMutant);
    }
}