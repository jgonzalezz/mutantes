package co.com.mercadolibre.api;

import org.junit.Test;


public class ApiRestTest {

    @Test
    public void isMutant() {

        String[] dna1 = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        String[] dna2 = {"ATGCGA", "CCGTGC", "TTATGT", "AGAAGG", "AGATTA", "TCACTG"};
        String[] dna3 = {"ATGCTA", "CCGAGC", "TTAATT", "AAGATG", "AGATTA", "TCTCAG"};
        String[] dna4 = {"ATGCTA", "CAGGGC", "TGAAGT", "AAGAGG", "AGACTA", "TCTCAG"};
        String[] dna5 = {"ATTCTA", "CAGAGC", "TGATGT", "AATAGT", "ATACTA", "TCTCAG"};
        String[] dna6 = {"ATTCTAAT", "CAGAGCCG", "TGATGTTT", "AATAGTAG", "ATACTACT", "TCTCAGAC"};



    }
}