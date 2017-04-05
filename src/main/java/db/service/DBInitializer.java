package db.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JsIdentity on 23/03/2017.
 */

@Component
public class DBInitializer {

    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    public DBInitializer(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }


    public  List<AttributeDefinition> getAttributeDefinitions(){
        List<AttributeDefinition> attributeDefinitions = new ArrayList<>();
        attributeDefinitions.add(new AttributeDefinition().withAttributeName("id").withAttributeType(ScalarAttributeType.S));

        return attributeDefinitions;
    }

    public List<KeySchemaElement> getKeyDefinitions(){
        List<KeySchemaElement> keyDefinitions = new ArrayList<>();
        keyDefinitions.add(new KeySchemaElement().withAttributeName("id").withKeyType(KeyType.HASH));

        return keyDefinitions;
    }

//    @PostConstruct
//    public void customerTable(){
//        this.createTable();
//    }

    public void createTable(String tableName){

        CreateTableRequest tableRequest = new CreateTableRequest()
                                                    .withTableName(tableName)
                                                    .withKeySchema(getKeyDefinitions())
                                                    .withAttributeDefinitions(getAttributeDefinitions())
                                                    .withProvisionedThroughput(new ProvisionedThroughput()
                                                            .withReadCapacityUnits(10L).withWriteCapacityUnits(5L));
        CreateTableResult tableResult= this.amazonDynamoDB.createTable(tableRequest);


        assert(tableResult.hashCode()>-1);
    }



}
