package co.com.mercadolibre.model.stats;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Stats {
    int count_mutant_dna;
    int count_human_dna;
    double ratio;
}
