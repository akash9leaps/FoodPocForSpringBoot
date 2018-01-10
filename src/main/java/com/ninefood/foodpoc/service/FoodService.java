package com.ninefood.foodpoc.service;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.ninefood.foodpoc.model.FoodModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

@Service
public class FoodService {

    @Autowired
    private CassandraConnector cassandraConnector;

    @Value("${cassandra.keyspace.name}")
    private String keyspaceName;

    private Session session;
    private MappingManager manager;
    private Mapper<FoodModel> mapper;


    @PostConstruct
    public void init() {
        try {
            session = cassandraConnector.getSession();
            manager = new MappingManager(session);
            mapper = manager.mapper(FoodModel.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initiate FoodService", e);
        }
    }

    public Result<FoodModel> getFoodById(String itemId) throws Exception {
        Result<FoodModel> result = null;
        Statement statement = QueryBuilder
                .select()
                .from(keyspaceName, "foodnineleaps")
                .where(eq("item_id", itemId)).setFetchSize(10);
        statement.setConsistencyLevel(cassandraConnector.getConsistencyLevel());
        try {
            ResultSet resultSet = session.execute(statement);
            result = mapper.map(resultSet);
            System.out.println(result);

        } catch (Exception e) {
            throw new Exception("Failed to search Food for itemId :" + itemId.toString(), e);
        }
        return result;
    }

    public String createFoodItem(FoodModel foodModel) throws Exception {
        mapper.save(foodModel);
        return foodModel.getItemId();
    }

    public void delete(String itemId) throws Exception {
        FoodModel foodModel = getFoodById(itemId).one();
        mapper.delete(foodModel);
    }
}
