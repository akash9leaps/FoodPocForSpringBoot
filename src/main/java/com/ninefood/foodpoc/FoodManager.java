package com.ninefood.foodpoc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodManager {


    private final static Logger logger = LoggerFactory.getLogger(FoodManager.class);

    public static void main(String[] args) {
        logger.debug("FoodManager is STARTing...");
        try {
            SpringApplication.run(FoodManager.class, args);
        }catch (Exception e){
            logger.debug("FoodManager is getting exception...");
            logger.error(e.getMessage(),e);
        }
    }
}
