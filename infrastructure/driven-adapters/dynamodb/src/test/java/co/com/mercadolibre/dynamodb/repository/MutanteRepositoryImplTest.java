package co.com.mercadolibre.dynamodb.repository;

import co.com.mercadolibre.model.mutante.Mutante;
import co.com.mercadolibre.model.mutante.gateways.MutanteRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MutanteRepositoryImpl.class})
public class MutanteRepositoryImplTest {

    @MockBean
    DynamoDBMapper mapper;
    MutanteRepository repository;

    @Before
    public void setUp() {
        repository = new MutanteRepositoryImpl(mapper);
    }

    @Test
    public void countMutant() {
        when(mapper.count(any(), any(DynamoDBScanExpression.class))).thenReturn(1);
        repository.countMutant(true);
    }

    @Test
    public void createMutant() {
        Mockito.doNothing().when(mapper).save(any());
        Mutante mutante=new Mutante();
        mutante.setMutant(true);
        mutante.setDna("dna");
        repository.createMutant(mutante);
    }
}