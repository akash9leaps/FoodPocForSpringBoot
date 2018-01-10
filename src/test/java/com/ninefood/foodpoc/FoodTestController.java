package com.ninefood.foodpoc;


import com.ninefood.foodpoc.service.FoodService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class FoodTestController extends FoodTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Mock
    FoodService foodService;

    private String json ="{\"itemId\":\"3\",\"itemPrice\":\"35\",\"name\":\"veg-burger\"}";
   
    
    private String updateJson ="{\"itemId\":\"2\",\"itemPrice\":\"35\",\"name\":\"veg-burger\"}";


    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testFoodItem() throws Exception{
        MvcResult result = mockMvc.perform(get("/fooditems/2")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

  @Test
    public void testFoodItemsForPost() throws Exception{

      MvcResult result = mockMvc.perform(post("/fooditems/additem")
              .accept(MediaType.APPLICATION_JSON)
              .content(json)
              .contentType(MediaType.APPLICATION_JSON))
              .andReturn();
      MockHttpServletResponse response = result.getResponse();
      assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    @Test
    public void testFoodItemForDelete() throws Exception{

        MvcResult result = mockMvc.perform(delete("/fooditems/deleteitem/3")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

     @Test
    public void testFoodItemForUpdate() throws Exception{

        MvcResult result= mockMvc.perform(patch("/fooditems/updateitem")
        .accept(MediaType.APPLICATION_JSON)
        .content(updateJson)
        .contentType(MediaType.APPLICATION_JSON))
        .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());

    }
	
    @Test
    public void testFoodItems() throws Exception{
        MvcResult result = mockMvc.perform(get("/fooditems/allfooditems")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

}
