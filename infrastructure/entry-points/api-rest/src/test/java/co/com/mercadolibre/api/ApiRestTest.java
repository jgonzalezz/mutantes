package co.com.mercadolibre.api;

import co.com.mercadolibre.model.stats.Stats;
import co.com.mercadolibre.usecase.mutante.MutantUseCase;
import co.com.mercadolibre.usecase.stats.StatsUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@WebMvcTest(controllers = ApiRest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApiRest.class})
public class ApiRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MutantUseCase mutantUseCase;

    @MockBean
    private StatsUseCase statsUseCase;


    @Test
    public void isNotMutant() throws Exception {
        String json = "{\n" +
                "    \"dna\": [\n" +
                "        \"GTGCGA\",\n" +
                "        \"CAGTGC\",\n" +
                "        \"TTATGT\",\n" +
                "        \"AGAAGG\",\n" +
                "        \"CCTCTA\",\n" +
                "        \"TCACTG\"\n" +
                "    ]\n" +
                "}";
        when(mutantUseCase.isMutant(any())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().is(403));
    }

    @Test
    public void isMutant() throws Exception {
        String json = "{\n" +
                "    \"dna\": [\n" +
                "        \"ATGCGA\",\n" +
                "        \"CAGTGC\",\n" +
                "        \"TTATGT\",\n" +
                "        \"AGAAGG\",\n" +
                "        \"CCCCTA\",\n" +
                "        \"TCACTG\"\n" +
                "    ]\n" +
                "}";


        when(mutantUseCase.isMutant(any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void getStats() throws Exception {
        when(statsUseCase.getStats()).thenReturn(Stats.builder().build());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/stats"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }


}