package com.ninefood.foodpoc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninefood.foodpoc.model.FoodModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FoodModelTest {

    @InjectMocks
    FoodModel foodModel;

    ObjectMapper objectMapper= new ObjectMapper();

    @Test
    public void foodModelTest() throws Exception{

        String jsonData = "{\"itemId\":\"2\",\"itemPrice\":20,\"name\":\"burger\"}";
        FoodModel foodModel=new FoodModel();
        foodModel.setItemId("2");
        foodModel.setItemPrice(20);
        foodModel.setName("burger");
        String testString = objectMapper.writeValueAsString(foodModel);
        assertEquals(jsonData, testString);
    }
}
