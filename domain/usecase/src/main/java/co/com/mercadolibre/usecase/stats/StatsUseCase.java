package co.com.mercadolibre.usecase.stats;

import co.com.mercadolibre.model.mutante.gateways.MutanteRepository;
import co.com.mercadolibre.model.stats.Stats;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StatsUseCase {

    private final MutanteRepository mutanteRepository;

    public Stats getStats(){
        int totalMutants = getTotalMutants(true);
        int totalHumans = getTotalHumans(false);
        double ratio = (totalMutants > 0 && totalHumans > 0 ?
                (double)totalMutants / totalHumans : 0.0);

        return  Stats.builder()
                .count_human_dna(totalHumans)
                .count_mutant_dna(totalMutants)
                .ratio(ratio)
                .build();
    }

    private int getTotalMutants(boolean isMutant){
        return mutanteRepository.countMutant(isMutant);
    }

    private int getTotalHumans(boolean isMutant){
        return mutanteRepository.countMutant(isMutant);
    }

}
