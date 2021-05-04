package co.com.mercadolibre.usecase.mutante;

import co.com.mercadolibre.model.mutante.Mutante;
import co.com.mercadolibre.model.mutante.gateways.MutanteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;

@Log4j2
@RequiredArgsConstructor
public class MutantUseCase {

    private boolean isMutant = false;
    private static final int NITROGEN_BASE_COUNT = 4;
    private static final int MUTANT_SEQUENCE_COUNT = 2;

    private final MutanteRepository mutanteRepository;

    //Validamos el dna y lo guardamos en la bd
    public boolean isMutant(String[] dna) {
        this.isMutant = searchSequencesInArray(dna);
        Mutante mutante = new Mutante();
        mutante.setMutant(this.isMutant);
        mutante.setDna(Arrays.toString(dna));
        mutanteRepository.createMutant(mutante);
        return isMutant;
    }

    // Buscamos las 2 secuencias necesarias en el array dna
    // La busqueda en el array se realiza en 4 flujos
    // Vertical(TopDown), Horizontal(LeftRight), Diagonales(RightLeft,LeftRight)
    private boolean searchSequencesInArray(String[] dna) {

        int count = 0;

        count += searchTopDown(dna);
        if (count >= MUTANT_SEQUENCE_COUNT) {
            return true;
        }
        count += searchLeftRight(dna);
        if (count >= MUTANT_SEQUENCE_COUNT) {
            return true;
        }
        count += searchDiagonalRightLeft(dna);
        if (count >= MUTANT_SEQUENCE_COUNT) {
            return true;
        }
        count += searchDiagonalLeftRight(dna);
        if (count >= MUTANT_SEQUENCE_COUNT) {
            return true;
        }

        return false;
    }

    private int searchTopDown(String[] dna) {
        int row = 0;
        int col = 0;
        Character currentCharacter;
        Character nextCharacter;
        int counter = 1;
        int dnaSequenceCount = 0;

        currentCharacter = dna[row].charAt(col);

        for (int c = 0; c < dna.length; c++) {
            for (int r = 1; r < dna.length; r++) {
                if ((nextCharacter = dna[r].charAt(c)) != null) {
                    if (nextCharacter.equals(currentCharacter)) {
                        counter++;
                    } else {
                        currentCharacter = nextCharacter;
                        counter = 1;
                    }
                    if (counter == NITROGEN_BASE_COUNT) {
                        dnaSequenceCount++;
                        if (dnaSequenceCount >= 2) {
                            log.info("searchTopDown "+dnaSequenceCount);
                            return dnaSequenceCount;
                        }
                        counter = 1;
                    }
                }
            }
            col++;
            if (col < dna.length) {
                counter = 1;
                currentCharacter = dna[row].charAt(col);
            }
        }
        log.info("searchTopDown "+dnaSequenceCount);
        return dnaSequenceCount;
    }

    private int searchLeftRight(String[] dna) {

        int row = 0;
        int col = 0;
        Character currentCharacter;
        Character nextCharacter;
        int counter = 1;
        int dnaSequenceCount = 0;

        currentCharacter = dna[row].charAt(col);

        for (int r = 0; r < dna.length; r++) {
            for (int c = 1; c < dna.length; c++) {
                if ((nextCharacter = dna[r].charAt(c)) != null) {
                    if (nextCharacter.equals(currentCharacter)) {
                        counter++;
                    } else {
                        currentCharacter = nextCharacter;
                        counter = 1;
                    }
                    if (counter == NITROGEN_BASE_COUNT) {
                        dnaSequenceCount++;
                        if (dnaSequenceCount >= 2) {
                            log.info("searchLeftRight "+dnaSequenceCount);
                            return dnaSequenceCount;
                        }
                        counter = 1;
                    }
                }
            }
            row++;
            if (row < dna.length) {
                counter = 1;
                currentCharacter = dna[row].charAt(col);
            }
        }
        log.info("searchLeftRight "+dnaSequenceCount);
        return dnaSequenceCount;
    }


    private int searchDiagonalRightLeft(String[] dna) {
        int row = 0;
        int col = 0;
        Character currentCharacter;
        Character nextCharacter;
        int counter = 1;
        int dnaSequenceCount = 0;

        currentCharacter = dna[row].charAt(col);

        for (Integer diag = 1 - dna.length;
             diag <= dna.length - 1;
             diag += 1) {
            for (Integer vert = Math.max(0, diag), horiz = -Math.min(0, diag);
                 vert < dna.length && horiz < dna.length;
                 vert += 1, horiz += 1) {

                nextCharacter = dna[vert].charAt(horiz);

                if (nextCharacter.equals(currentCharacter)) {
                    counter++;
                } else {
                    currentCharacter = nextCharacter;
                    counter = 1;
                }
                if (counter == NITROGEN_BASE_COUNT) {
                    dnaSequenceCount++;
                    if (dnaSequenceCount >= 2) {
                        log.info("searchDiagonalRightLeft "+dnaSequenceCount);
                        return dnaSequenceCount;
                    }
                    counter = 1;
                }
            }
            counter = 1;
        }
        log.info("searchDiagonalRightLeft "+dnaSequenceCount);
        return dnaSequenceCount;
    }

    private int searchDiagonalLeftRight(String[] dna) {

        int row = 0;
        int col = 0;
        Character currentCharacter;
        Character nextCharacter;
        int counter = 1;
        int dnaSequenceCount = 0;

        currentCharacter = dna[row].charAt(col);

        for (int k = 0; k <= 2 * (dna.length - 1); ++k) {
            int yMin = Math.max(0, k - dna.length + 1);
            int yMax = Math.min(dna.length - 1, k);
            for (int y = yMin; y <= yMax; ++y) {
                int x = k - y;

                nextCharacter = dna[y].charAt(x);

                if (nextCharacter.equals(currentCharacter)) {
                    counter++;
                } else {
                    currentCharacter = nextCharacter;
                    counter = 1;
                }
                if (counter == NITROGEN_BASE_COUNT) {
                    dnaSequenceCount++;
                    if (dnaSequenceCount >= 2) {
                        log.info("searchDiagonalLeftRight "+dnaSequenceCount);
                        return dnaSequenceCount;
                    }
                    counter = 1;
                }
            }
            counter = 1;
        }
        log.info("searchDiagonalLeftRight "+dnaSequenceCount);
        return dnaSequenceCount;
    }

}
