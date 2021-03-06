package co.com.mercadolibre.model.mutante;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "dna-mutant")
public class Mutante implements Serializable {

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    String id;

    @DynamoDBAttribute
    boolean mutant;

    @DynamoDBAttribute
    String dna;
}
