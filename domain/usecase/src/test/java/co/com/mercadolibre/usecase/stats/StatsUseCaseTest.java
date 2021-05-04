package co.com.mercadolibre.usecase.stats;

import co.com.mercadolibre.model.mutante.gateways.MutanteRepository;
import co.com.mercadolibre.model.stats.Stats;
import co.com.mercadolibre.model.stats.gateways.StatsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatsUseCaseTest {

    @Mock
    private MutanteRepository repository;
    private StatsUseCase useCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.useCase = new StatsUseCase(repository);
    }

    @Test
    public void getStats() {
        when(repository.countMutant(true)).thenReturn(1);
        when(repository.countMutant(false)).thenReturn(2);
        Stats stats = useCase.getStats();
        assertEquals(0.5, 0.5, stats.getRatio());
    }
}