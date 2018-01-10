package com.ninefood.foodpoc.controller;

import com.datastax.driver.mapping.Result;
import com.ninefood.foodpoc.model.FoodModel;
import com.ninefood.foodpoc.service.FoodService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/fooditems")
@Api(value = "API to perform CRUD operation in a Food database maintained in apache cassandra",
        description = "This API provides the capability to perform CRUD operation in a Food " +
                "database maintained in apache cassandra", produces = "application/json")

public class FoodController {

    private static final Logger logger = LoggerFactory.getLogger(FoodController.class);

    @Autowired
    private FoodService foodService;

    private static final String NO_RECORD = "Food not found for Item Id : ";

    @ApiOperation(value = "Search Food by itemId", produces = "application/json")
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public ResponseEntity<Object> searchFoodById(@ApiParam(name = "itemId",
            value = "The Id of the Food to be viewed",
            required = true) @PathVariable String itemId) {
        logger.debug("Searching for food with itemId :: {}", itemId);
        Result<FoodModel> foodResult = null;
        ResponseEntity<Object> response = null;
        try {
            foodResult = foodService.getFoodById(itemId);
            if (foodResult == null) {
                response = new ResponseEntity<Object>(NO_RECORD + itemId, HttpStatus.OK);
            } else {
                response = new ResponseEntity<Object>(foodResult.one(), HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @ApiOperation(value = "Create a new Food", consumes = "application/json")

    @RequestMapping(value = "/additem" ,method = RequestMethod.POST)
    public ResponseEntity<Object> createFood(@RequestBody FoodModel data) {
        ResponseEntity<Object> response = null;
        try {
            String itemId = foodService.createFoodItem(data);
            response = new ResponseEntity<Object>("Food created successfully with Id :" +
                    itemId, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @ApiOperation(value = "Delete a Food Object from Database", consumes = "application/json")
                @ApiParam(name = "itemId", value = "ItemID to delete",
                    required = true)
    @RequestMapping(value = "/deleteitem/{itemId}",method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(
            @PathVariable(name = "itemId") String itemId) {
        logger.debug("Deleting Food with itemId :: {}", itemId);
        ResponseEntity<Object> response = null;
        try {
            foodService.delete(itemId);
            response = new ResponseEntity<Object>("Item deleted successfully with Id :" +
                    itemId, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @ApiOperation(value = "Update a Food Object from Database", consumes = "application/json")
    @ApiParam(name = "itemId", value = "ItemID to update",
            required = true)
    @RequestMapping(value = "/updateitem/{itemId}",method = RequestMethod.PATCH)
    public ResponseEntity<Object> update(@RequestBody FoodModel data) {
        ResponseEntity<Object> response = null;
        try {
            String itemId=foodService.update(data);
            response = new ResponseEntity<Object>("Item updated successfully with Id :" +
                    itemId, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

}
