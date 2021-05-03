package co.com.mercadolibre.dynamodb.repository;

import co.com.mercadolibre.model.mutante.Mutante;
import co.com.mercadolibre.model.mutante.gateways.MutanteRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MutanteRepositoryImpl implements MutanteRepository {

    @Autowired
    private DynamoDBMapper mapper;

    @Override
    public int countMutant (boolean mutant) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withN((mutant == true ? "1" : "0")));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("mutant = :val1").withExpressionAttributeValues(eav);
        return mapper.count(Mutante.class, scanExpression);
    }

    @Override
    public Mutante createMutant(Mutante mutante) {
        mapper.save(mutante);
        return mutante;
    }

}
