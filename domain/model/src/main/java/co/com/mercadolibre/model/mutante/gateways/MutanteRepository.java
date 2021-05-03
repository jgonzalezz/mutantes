package co.com.mercadolibre.model.mutante.gateways;

import co.com.mercadolibre.model.mutante.Mutante;

public interface MutanteRepository {
    int countMutant(boolean isMutant);
    Mutante createMutant(Mutante mutante);
}
