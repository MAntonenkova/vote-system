package edu.pet.votesystem.service;

import edu.pet.votesystem.model.Restaurant;
import edu.pet.votesystem.rest.RestaurantController;
import edu.pet.votesystem.view.DishRequest;
import edu.pet.votesystem.view.RestaurantsResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:spring/springContext.xml"})
public class RestaurantServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void getAllRestaurants() {
        List<RestaurantsResponse> restaurants = service.getAllRestaurants();
        Assert.assertEquals(9, restaurants.size());
    }

    @Test
    public void getRestaurant() {
        RestaurantsResponse restaurant = service.getRestaurant(9);
        Assert.assertEquals(3, restaurant.getDishes().size());
    }

    @Test
    public void addRestaurant() throws Exception {
        int restCount = service.getAllRestaurants().size();
        service.updateRestaurant(null,"New restaurant");
        Assert.assertEquals(++restCount, service.getAllRestaurants().size());
    }

    @Test
    public void editRestaurant() throws Exception{
        String newName = "Mushrooms";
        service.updateRestaurant(1, newName);
        Assert.assertEquals(newName, service.getRestaurant(1).getRestName());
    }

    @Test
    public void addDish(){
        DishRequest dishRequest = new DishRequest();
        dishRequest.setDishName("Cream cheese and smoked salmon");
        dishRequest.setDishPrice(750);
        service.addDish(8, dishRequest);
        Assert.assertEquals(2, service.getRestaurant(8).getDishes().size());
    }

    @Test
    public void editDish(){
        DishRequest dishRequest = new DishRequest();
        dishRequest.setDishName("Bonbons");
        dishRequest.setDishPrice(300);
        service.editDish(9, 12, dishRequest);
        Assert.assertEquals("Bonbons", service.getDish(12).getDishName());
    }
}