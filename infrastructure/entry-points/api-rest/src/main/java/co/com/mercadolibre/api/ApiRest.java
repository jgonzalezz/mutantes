package co.com.mercadolibre.api;

import co.com.mercadolibre.usecase.mutante.MutantUseCase;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Log4j2
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {

    private final MutantUseCase mutantUseCase;
    private static final Set<Character> CHARACTERS =
            new HashSet<>(Arrays.asList('A', 'T', 'C', 'G'));

    @PostMapping(value = "/mutant")
    public ResponseEntity isMutant(@RequestBody String body) {

        String dna[] = bodyToArray(body);
        if(!dnaValidations(dna)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if (mutantUseCase.isMutant(dna)) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    private String[] bodyToArray(String body) throws JsonSyntaxException {
        Gson g = new Gson();
        JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
        JsonArray dnaArray = jsonObject.getAsJsonArray("dna");
        Type collectionType = new TypeToken<List<String>>(){}.getType();
        List<String> dnaList = g.fromJson(dnaArray.toString(), collectionType);
        return dnaList.toArray(new String[]{});
    }

    private boolean dnaValidations(String[] dna) {
        boolean isValid = false;
        //validar si es cuadrada

        if(dna.length < 4){
            isValid = false;
        }
        if(charactersValid(dna)){
            isValid = true;
        }
        return isValid;
    }

    boolean charactersValid(String[] dna) {
        for (String s : dna) {
            for (int k = 0; k < s.length(); k++) {
                char c = s.charAt(k);
                if (!CHARACTERS.contains(c)) {
                    return false;
                }
            }
        }
        return true;
    }

}