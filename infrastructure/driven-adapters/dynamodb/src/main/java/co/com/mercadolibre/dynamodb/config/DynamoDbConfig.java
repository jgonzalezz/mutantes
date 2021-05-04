package co.com.mercadolibre.dynamodb.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class DynamoDbConfig {

    @Value("${aws.region}")
    private String region;

    public static final String SERVICE_ENDPOINT = "dynamodb.us-east-1.amazonaws.com";
    public static final String REGION = "us-east-1";
    public static final String ACCESS_KEY = "AKIARSGQREU44SVIXEN2";
    public static final String SECRET_KEY = "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";

    @Bean
    public DynamoDBMapper mapper() {
        return new DynamoDBMapper(amazonDynamoDBConfig());
    }

    private AmazonDynamoDB amazonDynamoDBConfig() {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(SERVICE_ENDPOINT, REGION))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY))).build();
    }

}
