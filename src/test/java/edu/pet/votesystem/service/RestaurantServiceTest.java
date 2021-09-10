package edu.pet.votesystem.service;

import edu.pet.votesystem.model.Dish;
import edu.pet.votesystem.model.Restaurant;
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

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:spring/springContext.xml"})
public class RestaurantServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantServiceTest.class);
    @Autowired
    private RestaurantController controller;

    @Autowired
    private RestaurantService service;


    @Test
    public void restaurantInfo(){
        RestaurantRequest request = new RestaurantRequest();
        request.setRestaurantName("Geraldine");

        RestaurantResponse restaurantInfo = controller.getRestaurantInfo(request);
        Assert.assertEquals(3, restaurantInfo.getDishes().size());
    }

    @Test
    public void findRestaurants() {
        List<Restaurant> restaurants = service.findRestaurants();
        System.out.println("OK");
        restaurants.forEach(r -> System.out.println(r.getRestaurantId() + r.getRestaurantName() + r.getDishes().size()));
    }

    @Test
    public void findDishes(){
        List<Dish> dishes = service.findDishes();
        System.out.println("Ok");
        dishes.forEach(d -> System.out.println(d.getRestaurant().getRestaurantName()));
    }

    @Test
    public void findDish(){
        Dish dish = service.getDish(8);
        System.out.println(dish.getRestaurant().getRestaurantName());
    }

    @Test
    public void findRestaurantsWithDishes() {
        List<Restaurant> restaurants = service.findFullRestaurants();
        System.out.println("OK");
        restaurants.forEach(r -> System.out.println(r.getRestaurantId() + ", "  + r.getRestaurantName()+ ", " + r.getDishes().size()));
    }
}