package com.ninefood.foodpoc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodManagerTest {

    @Test
    public void mainTest(){
        FoodManager.main(new String[]{
                "--spring.main.web-environment=false",
                "--spring.autoconfigure.exclude=this is for main class test",
        });
    }
}
