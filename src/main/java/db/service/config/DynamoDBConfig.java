package db.service.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by JsIdentity on 22/03/2017.
 */

@Configuration
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAwsAccessKey;
    @Value("${amazon.aws.secretkey}")
    private String amazonAwsSecretKey;


    @Bean
    public AWSCredentials awsCredentials(){
        return new BasicAWSCredentials(amazonAwsAccessKey, amazonAwsSecretKey);
    }


    @Bean
    public AmazonDynamoDB amazonDynamoDB(AWSCredentials awsCredentials){
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();

        if(!StringUtils.isNullOrEmpty(amazonDynamoDBEndpoint)){
            amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
        }


        return amazonDynamoDB;

    }


}
