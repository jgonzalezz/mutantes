package co.com.mercadolibre.model.mutante;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Mutante {
    String dna;
    boolean mutant;
}
