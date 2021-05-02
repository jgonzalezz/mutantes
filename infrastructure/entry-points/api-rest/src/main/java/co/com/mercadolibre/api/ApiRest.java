package co.com.mercadolibre.api;
import co.com.mercadolibre.usecase.mutante.MutantUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {

    private final MutantUseCase mutantUseCase;

    @GetMapping(path = "/path")
    public Boolean commandName() {
        String[] dna1 = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        String[] dna2 = {"ATGCGA","CCGTGC","TTATGT","AGAAGG","AGATTA","TCACTG"};
        String[] dna3 = {"ATGCTA","CCGAGC","TTAATT","AAGATG","AGATTA","TCTCAG"};
        String[] dna4 = {"ATGCTA","CAGGGC","TGAAGT","AAGAGG","AGACTA","TCTCAG"};
        String[] dna5 = {"ATTCTA","CAGAGC","TGATGT","AATAGT","ATACTA","TCTCAG"};


        return mutantUseCase.isMutant(dna5);
    }
}