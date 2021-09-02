package edu.pet.votesystem.service;

import edu.pet.votesystem.rest.RestaurantController;
import edu.pet.votesystem.view.RestaurantRequest;
import edu.pet.votesystem.view.RestaurantResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:spring/springContext.xml"})
public class RestaurantServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantServiceTest.class);
    @Autowired
    private RestaurantController controller;

    @Test
    public void restaurantInfo(){
        RestaurantRequest request = new RestaurantRequest();
        request.setRestaurantName("Geraldine");

        RestaurantResponse restaurantInfo = controller.getRestaurantInfo(request);
        Assert.assertEquals(3, restaurantInfo.getDishes().size());
    }

}